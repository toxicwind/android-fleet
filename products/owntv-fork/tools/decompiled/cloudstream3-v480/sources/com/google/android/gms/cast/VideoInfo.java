package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class VideoInfo extends AbstractSafeParcelable {
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private static final Logger zza = new Logger("VideoInfo");
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzds();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    public static class Builder {
        private int zza;
        private int zzb;
        private int zzc;

        public VideoInfo build() {
            return new VideoInfo(this.zza, this.zzb, this.zzc);
        }

        public Builder setHdrType(int i) {
            this.zzc = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.zzb = i;
            return this;
        }

        public Builder setWidth(int i) {
            this.zza = i;
            return this;
        }
    }

    VideoInfo(int i, int i2, int i3) {
        this.zzb = i;
        this.zzc = i2;
        this.zzd = i3;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004b A[Catch: JSONException -> 0x0069, TRY_ENTER, TryCatch #0 {JSONException -> 0x0069, blocks: (B:6:0x0005, B:29:0x0057, B:28:0x004b), top: B:34:0x0005 }] */
    static VideoInfo zza(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            String string = jSONObject.getString("hdrType");
            int iHashCode = string.hashCode();
            int i = 1;
            if (iHashCode != 3218) {
                if (iHashCode != 103158) {
                    if (iHashCode != 113729) {
                        if (iHashCode == 99136405 && string.equals("hdr10")) {
                            i = 2;
                        } else {
                            zza.d("Unknown HDR type: %s", string);
                            i = 0;
                        }
                    } else if (!string.equals("sdr")) {
                        zza.d("Unknown HDR type: %s", string);
                        i = 0;
                    }
                } else if (string.equals("hdr")) {
                    i = 4;
                } else {
                    zza.d("Unknown HDR type: %s", string);
                    i = 0;
                }
            } else if (string.equals("dv")) {
                i = 3;
            } else {
                zza.d("Unknown HDR type: %s", string);
                i = 0;
            }
            return new VideoInfo(jSONObject.getInt("width"), jSONObject.getInt("height"), i);
        } catch (JSONException e) {
            zza.d(e, "Error while creating a VideoInfo instance from JSON", new Object[0]);
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.zzc == videoInfo.getHeight() && this.zzb == videoInfo.getWidth() && this.zzd == videoInfo.getHdrType();
    }

    public int getHdrType() {
        return this.zzd;
    }

    public int getHeight() {
        return this.zzc;
    }

    public int getWidth() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(this.zzd));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.writeInt(parcel, 4, getHdrType());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final JSONObject zzb() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.zzb);
            jSONObject.put("height", this.zzc);
            int i = this.zzd;
            if (i == 1) {
                str = "sdr";
            } else if (i == 2) {
                str = "hdr10";
            } else if (i != 3) {
                str = i != 4 ? null : "hdr";
            } else {
                str = "dv";
            }
            jSONObject.put("hdrType", str);
            return jSONObject;
        } catch (JSONException unused) {
            zza.e("Failed to transform VideoInfo into Json", new Object[0]);
            return new JSONObject();
        }
    }
}
