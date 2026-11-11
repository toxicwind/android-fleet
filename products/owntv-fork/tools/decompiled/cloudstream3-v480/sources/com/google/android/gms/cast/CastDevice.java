package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import j$.util.DesugarCollections;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = new zzs();
    final String zza;
    final Boolean zzb;
    private final String zzc;
    private InetAddress zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final int zzh;
    private final List zzi;
    private final com.google.android.gms.cast.internal.zzp zzj;
    private final int zzk;
    private final String zzl;
    private final String zzm;
    private final int zzn;
    private final String zzo;
    private final byte[] zzp;
    private final String zzq;
    private final boolean zzr;
    private final com.google.android.gms.cast.internal.zzaa zzs;
    private final Integer zzt;

    CastDevice(String str, String str2, String str3, String str4, String str5, int i, List list, int i2, int i3, String str6, String str7, int i4, String str8, byte[] bArr, String str9, boolean z, com.google.android.gms.cast.internal.zzaa zzaaVar, Integer num, Boolean bool) {
        this.zzc = zzf(str);
        String strZzf = zzf(str2);
        this.zza = strZzf;
        if (!TextUtils.isEmpty(strZzf)) {
            try {
                this.zzd = InetAddress.getByName(strZzf);
            } catch (UnknownHostException e) {
                Log.i("CastDevice", "Unable to convert host address (" + this.zza + ") to ipaddress: " + e.getMessage());
            }
        }
        this.zze = zzf(str3);
        this.zzf = zzf(str4);
        this.zzg = zzf(str5);
        this.zzh = i;
        this.zzi = list == null ? new ArrayList() : list;
        this.zzk = i3;
        this.zzl = zzf(str6);
        this.zzm = str7;
        this.zzn = i4;
        this.zzo = str8;
        this.zzp = bArr;
        this.zzq = str9;
        this.zzr = z;
        this.zzs = zzaaVar;
        this.zzt = num;
        this.zzb = bool;
        this.zzj = new com.google.android.gms.cast.internal.zzp(i2, zzaaVar);
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        ClassLoader classLoader;
        if (bundle == null || (classLoader = CastDevice.class.getClassLoader()) == null) {
            return null;
        }
        bundle.setClassLoader(classLoader);
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    private static String zzf(String str) {
        return str == null ? "" : str;
    }

    public boolean equals(Object obj) {
        int i;
        byte[] bArr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        String str = this.zzc;
        if (str == null) {
            return castDevice.zzc == null;
        }
        if (CastUtils.zze(str, castDevice.zzc) && CastUtils.zze(this.zzd, castDevice.zzd) && CastUtils.zze(this.zzf, castDevice.zzf) && CastUtils.zze(this.zze, castDevice.zze)) {
            String str2 = this.zzg;
            if (CastUtils.zze(str2, castDevice.zzg) && (i = this.zzh) == castDevice.zzh && CastUtils.zze(this.zzi, castDevice.zzi) && this.zzj.zza() == castDevice.zzj.zza() && this.zzk == castDevice.zzk && CastUtils.zze(this.zzl, castDevice.zzl) && CastUtils.zze(Integer.valueOf(this.zzn), Integer.valueOf(castDevice.zzn)) && CastUtils.zze(this.zzo, castDevice.zzo) && CastUtils.zze(this.zzm, castDevice.zzm) && CastUtils.zze(str2, castDevice.getDeviceVersion()) && i == castDevice.getServicePort() && ((((bArr = this.zzp) == null && castDevice.zzp == null) || Arrays.equals(bArr, castDevice.zzp)) && CastUtils.zze(this.zzq, castDevice.zzq) && this.zzr == castDevice.zzr && CastUtils.zze(zzc(), castDevice.zzc()) && CastUtils.zze(Boolean.valueOf(zze()), Boolean.valueOf(castDevice.zze())))) {
                return true;
            }
        }
        return false;
    }

    public String getDeviceId() {
        String str = this.zzc;
        return str.startsWith("__cast_nearby__") ? str.substring(16) : str;
    }

    public String getDeviceVersion() {
        return this.zzg;
    }

    public String getFriendlyName() {
        return this.zze;
    }

    public WebImage getIcon(int i, int i2) {
        List<WebImage> list = this.zzi;
        WebImage webImage = null;
        if (list.isEmpty()) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            return (WebImage) list.get(0);
        }
        WebImage webImage2 = null;
        for (WebImage webImage3 : list) {
            int width = webImage3.getWidth();
            int height = webImage3.getHeight();
            if (width < i || height < i2) {
                if (width < i && height < i2 && (webImage2 == null || (webImage2.getWidth() < width && webImage2.getHeight() < height))) {
                    webImage2 = webImage3;
                }
            } else if (webImage == null || (webImage.getWidth() > width && webImage.getHeight() > height)) {
                webImage = webImage3;
            }
        }
        if (webImage != null) {
            return webImage;
        }
        return webImage2 != null ? webImage2 : (WebImage) list.get(0);
    }

    public List<WebImage> getIcons() {
        return DesugarCollections.unmodifiableList(this.zzi);
    }

    public InetAddress getInetAddress() {
        return this.zzd;
    }

    @Deprecated
    public Inet4Address getIpAddress() {
        if (hasIPv4Address()) {
            return (Inet4Address) this.zzd;
        }
        return null;
    }

    public String getModelName() {
        return this.zzf;
    }

    public int getServicePort() {
        return this.zzh;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (!this.zzj.zzb(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCapability(int i) {
        return this.zzj.zzb(i);
    }

    public boolean hasIPv4Address() {
        return getInetAddress() instanceof Inet4Address;
    }

    public boolean hasIPv6Address() {
        return getInetAddress() instanceof Inet6Address;
    }

    public boolean hasIcons() {
        return !this.zzi.isEmpty();
    }

    public int hashCode() {
        String str = this.zzc;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean isOnLocalNetwork() {
        return (this.zzc.startsWith("__cast_nearby__") || this.zzr) ? false : true;
    }

    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            return CastUtils.zze(getDeviceId(), castDevice.getDeviceId());
        }
        String str = this.zzo;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = castDevice.zzo;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return CastUtils.zze(str, str2);
    }

    public void putInBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
    }

    public String toString() {
        String strConcat;
        com.google.android.gms.cast.internal.zzp zzpVar = this.zzj;
        if (zzpVar.zzb(64)) {
            strConcat = "[dynamic group]";
        } else if (zzpVar.zzc()) {
            strConcat = "[static group]";
        } else {
            strConcat = zzpVar.zzd() ? "[speaker pair]" : "";
        }
        if (zzpVar.zzb(262144)) {
            strConcat = strConcat.concat("[cast connect]");
        }
        String str = this.zze;
        Locale locale = Locale.ROOT;
        int i = CastUtils.zza;
        if (!TextUtils.isEmpty(str)) {
            int length = str.length();
            if (length <= 2) {
                str = length == 2 ? "xx" : "x";
            } else {
                str = String.format(Locale.ROOT, "%c%d%c", Character.valueOf(str.charAt(0)), Integer.valueOf(length - 2), Character.valueOf(str.charAt(length - 1)));
            }
        }
        return String.format(locale, "\"%s\" (%s) %s", str, this.zzc, strConcat);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        String str = this.zzc;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, str, false);
        SafeParcelWriter.writeString(parcel, 3, this.zza, false);
        SafeParcelWriter.writeString(parcel, 4, getFriendlyName(), false);
        SafeParcelWriter.writeString(parcel, 5, getModelName(), false);
        SafeParcelWriter.writeString(parcel, 6, getDeviceVersion(), false);
        SafeParcelWriter.writeInt(parcel, 7, getServicePort());
        SafeParcelWriter.writeTypedList(parcel, 8, getIcons(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzj.zza());
        SafeParcelWriter.writeInt(parcel, 10, this.zzk);
        SafeParcelWriter.writeString(parcel, 11, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzm, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzn);
        SafeParcelWriter.writeString(parcel, 14, this.zzo, false);
        SafeParcelWriter.writeByteArray(parcel, 15, this.zzp, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzq, false);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzr);
        SafeParcelWriter.writeParcelable(parcel, 18, zzc(), i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 19, this.zzt, false);
        SafeParcelWriter.writeBooleanObject(parcel, 20, Boolean.valueOf(zze()), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zzj.zza();
    }

    public final int zzb() {
        com.google.android.gms.cast.internal.zzp zzpVar = this.zzj;
        if (zzpVar.zzb(64)) {
            return 4;
        }
        if (zzpVar.zzc()) {
            return 3;
        }
        if (zzpVar.zzd()) {
            return 5;
        }
        return hasCapability(1) ? 2 : 1;
    }

    public final com.google.android.gms.cast.internal.zzaa zzc() {
        com.google.android.gms.cast.internal.zzaa zzaaVar = this.zzs;
        return (zzaaVar == null && this.zzj.zzd()) ? com.google.android.gms.cast.internal.zzz.zza(1) : zzaaVar;
    }

    public final String zzd() {
        return this.zzm;
    }

    public final boolean zze() {
        Boolean bool = this.zzb;
        if (bool != null) {
            return bool.booleanValue();
        }
        int i = this.zzk;
        return i != -1 && (i & 2) > 0;
    }
}
