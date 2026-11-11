package com.google.android.gms.cast.internal;

import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLiveSeekableRange;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzar extends zzd {
    public static final String zzb;
    final zzav zzc;
    final zzav zzd;
    final zzav zze;
    final zzav zzf;
    final zzav zzg;
    final zzav zzh;
    final zzav zzi;
    final zzav zzj;
    final zzav zzk;
    final zzav zzl;
    final zzav zzm;
    final zzav zzn;
    final zzav zzo;
    final zzav zzp;
    final zzav zzq;
    final zzav zzr;
    final zzav zzs;
    final zzav zzt;
    final zzav zzu;
    private long zzv;
    private MediaStatus zzw;
    private Long zzx;
    private zzao zzy;
    private int zzz;

    static {
        int i = CastUtils.zza;
        zzb = "urn:x-cast:com.google.cast.media";
    }

    public zzar(String str) {
        super(zzb, "MediaControlChannel", null);
        this.zzz = -1;
        zzav zzavVar = new zzav(86400000L, "load");
        this.zzc = zzavVar;
        zzav zzavVar2 = new zzav(86400000L, "pause");
        this.zzd = zzavVar2;
        zzav zzavVar3 = new zzav(86400000L, "play");
        this.zze = zzavVar3;
        zzav zzavVar4 = new zzav(86400000L, "stop");
        this.zzf = zzavVar4;
        zzav zzavVar5 = new zzav(10000L, "seek");
        this.zzg = zzavVar5;
        zzav zzavVar6 = new zzav(86400000L, MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
        this.zzh = zzavVar6;
        zzav zzavVar7 = new zzav(86400000L, "mute");
        this.zzi = zzavVar7;
        zzav zzavVar8 = new zzav(86400000L, NotificationCompat.CATEGORY_STATUS);
        this.zzj = zzavVar8;
        zzav zzavVar9 = new zzav(86400000L, "activeTracks");
        this.zzk = zzavVar9;
        zzav zzavVar10 = new zzav(86400000L, "trackStyle");
        this.zzl = zzavVar10;
        zzav zzavVar11 = new zzav(86400000L, "queueInsert");
        this.zzm = zzavVar11;
        zzav zzavVar12 = new zzav(86400000L, "queueUpdate");
        this.zzn = zzavVar12;
        zzav zzavVar13 = new zzav(86400000L, "queueRemove");
        this.zzo = zzavVar13;
        zzav zzavVar14 = new zzav(86400000L, "queueReorder");
        this.zzp = zzavVar14;
        zzav zzavVar15 = new zzav(86400000L, "queueFetchItemIds");
        this.zzq = zzavVar15;
        zzav zzavVar16 = new zzav(86400000L, "queueFetchItemRange");
        this.zzs = zzavVar16;
        this.zzr = new zzav(86400000L, "queueFetchItems");
        zzav zzavVar17 = new zzav(86400000L, "setPlaybackRate");
        this.zzt = zzavVar17;
        zzav zzavVar18 = new zzav(86400000L, "skipAd");
        this.zzu = zzavVar18;
        zzc(zzavVar);
        zzc(zzavVar2);
        zzc(zzavVar3);
        zzc(zzavVar4);
        zzc(zzavVar5);
        zzc(zzavVar6);
        zzc(zzavVar7);
        zzc(zzavVar8);
        zzc(zzavVar9);
        zzc(zzavVar10);
        zzc(zzavVar11);
        zzc(zzavVar12);
        zzc(zzavVar13);
        zzc(zzavVar14);
        zzc(zzavVar15);
        zzc(zzavVar16);
        zzc(zzavVar16);
        zzc(zzavVar17);
        zzc(zzavVar18);
        zzT();
    }

    private final long zzR(double d, long j, long j2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.zzv;
        if (jElapsedRealtime < 0) {
            jElapsedRealtime = 0;
        }
        if (jElapsedRealtime == 0) {
            return j;
        }
        long j3 = j + ((long) (jElapsedRealtime * d));
        if (j2 > 0 && j3 > j2) {
            return j2;
        }
        if (j3 >= 0) {
            return j3;
        }
        return 0L;
    }

    private static zzaq zzS(JSONObject jSONObject) {
        MediaError mediaErrorZza = MediaError.zza(jSONObject);
        zzaq zzaqVar = new zzaq();
        int i = CastUtils.zza;
        zzaqVar.zza = jSONObject.has("customData") ? jSONObject.optJSONObject("customData") : null;
        zzaqVar.zzb = mediaErrorZza;
        return zzaqVar;
    }

    private final void zzT() {
        this.zzv = 0L;
        this.zzw = null;
        Iterator it = zza().iterator();
        while (it.hasNext()) {
            ((zzav) it.next()).zzc(2002);
        }
    }

    private final void zzU(JSONObject jSONObject, String str) {
        if (jSONObject.has("sequenceNumber")) {
            this.zzz = jSONObject.optInt("sequenceNumber", -1);
        } else {
            this.zza.w(str.concat(" message is missing a sequence number."), new Object[0]);
        }
    }

    private final void zzV() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzc();
        }
    }

    private final void zzW() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzd();
        }
    }

    private final void zzX() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzk();
        }
    }

    private final void zzY() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzm();
        }
    }

    private final boolean zzZ() {
        return this.zzz != -1;
    }

    private static int[] zzaa(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            iArr[i] = jSONArray.getInt(i);
        }
        return iArr;
    }

    public final long zzB(zzat zzatVar) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "GET_STATUS");
            MediaStatus mediaStatus = this.zzw;
            if (mediaStatus != null) {
                jSONObject.put("mediaSessionId", mediaStatus.zzb());
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzj.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzC(zzat zzatVar, MediaSeekOptions mediaSeekOptions) throws IllegalStateException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        long position = mediaSeekOptions.isSeekToInfinite() ? 4294967296000L : mediaSeekOptions.getPosition();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "SEEK");
            jSONObject.put("mediaSessionId", zzn());
            jSONObject.put("currentTime", CastUtils.millisecToSec(position));
            if (mediaSeekOptions.getResumeState() == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (mediaSeekOptions.getResumeState() == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (mediaSeekOptions.getCustomData() != null) {
                jSONObject.put("customData", mediaSeekOptions.getCustomData());
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzx = Long.valueOf(position);
        this.zzg.zzb(jZzd, new zzam(this, zzatVar));
        return jZzd;
    }

    public final long zzE(zzat zzatVar, double d, JSONObject jSONObject) throws IllegalStateException, zzap {
        if (this.zzw == null) {
            throw new zzap();
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "SET_PLAYBACK_RATE");
            jSONObject2.put("playbackRate", d);
            Preconditions.checkNotNull(this.zzw, "mediaStatus should not be null");
            jSONObject2.put("mediaSessionId", this.zzw.zzb());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzt.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzF(zzat zzatVar, boolean z, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzn());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzi.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzG(zzat zzatVar, double d, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Volume cannot be " + d);
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzn());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("level", d);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzh.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzI(zzat zzatVar) throws IllegalStateException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "SKIP_AD");
            jSONObject.put("mediaSessionId", zzn());
        } catch (JSONException e) {
            this.zza.w(String.format(Locale.ROOT, "Error creating SkipAd message: %s", e.getMessage()), new Object[0]);
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzu.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzJ(zzat zzatVar, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "STOP");
            jSONObject2.put("mediaSessionId", zzn());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzf.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final MediaInfo zzK() {
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getMediaInfo();
    }

    public final MediaStatus zzL() {
        return this.zzw;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void zzO(String str) {
        int i;
        int iZza;
        boolean z;
        MediaStatus mediaStatus;
        int[] iArrZzaa;
        Logger logger = this.zza;
        logger.d("message received: %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("type");
            long jOptLong = jSONObject.optLong("requestId", -1L);
            switch (string.hashCode()) {
                case -1830647528:
                    if (string.equals(MediaError.ERROR_TYPE_LOAD_CANCELLED)) {
                        this.zzc.zzd(jOptLong, RemoteMediaPlayer.STATUS_CANCELED, zzS(jSONObject));
                    }
                    break;
                case -1790231854:
                    if (string.equals("QUEUE_ITEMS")) {
                        this.zzr.zzd(jOptLong, 0, null);
                        zzU(jSONObject, "QUEUE_ITEMS");
                        if (this.zzy != null) {
                            JSONArray jSONArray = jSONObject.getJSONArray("items");
                            MediaQueueItem[] mediaQueueItemArr = new MediaQueueItem[jSONArray.length()];
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                mediaQueueItemArr[i2] = new MediaQueueItem.Builder(jSONArray.getJSONObject(i2)).build();
                            }
                            this.zzy.zzg(mediaQueueItemArr);
                            break;
                        }
                    }
                    break;
                case -1125000185:
                    if (string.equals("INVALID_REQUEST")) {
                        logger.w("received unexpected error: Invalid Request.", new Object[0]);
                        Iterator it = zza().iterator();
                        while (it.hasNext()) {
                            ((zzav) it.next()).zzd(jOptLong, 2001, zzS(jSONObject));
                        }
                    }
                    break;
                case -262628938:
                    if (string.equals(MediaError.ERROR_TYPE_LOAD_FAILED)) {
                        this.zzc.zzd(jOptLong, 2100, zzS(jSONObject));
                    }
                    break;
                case 66247144:
                    if (string.equals(MediaError.ERROR_TYPE_ERROR)) {
                        Iterator it2 = zza().iterator();
                        while (it2.hasNext()) {
                            ((zzav) it2.next()).zzd(jOptLong, 2100, zzS(jSONObject));
                        }
                        if (this.zzy != null) {
                            this.zzy.zzb(MediaError.zza(jSONObject));
                        }
                    }
                    break;
                case 154411710:
                    if (string.equals("QUEUE_CHANGE")) {
                        this.zzs.zzd(jOptLong, 0, null);
                        zzU(jSONObject, "QUEUE_CHANGE");
                        if (this.zzy != null) {
                            String string2 = jSONObject.getString("changeType");
                            int[] iArrZzaa2 = zzaa(jSONObject.getJSONArray("itemIds"));
                            int iOptInt = jSONObject.optInt("insertBefore", 0);
                            if (iArrZzaa2 != null) {
                                switch (string2.hashCode()) {
                                    case -2130463047:
                                        if (string2.equals("INSERT")) {
                                            this.zzy.zzf(iArrZzaa2, iOptInt);
                                        }
                                        break;
                                    case -1881281404:
                                        if (string2.equals("REMOVE")) {
                                            this.zzy.zzh(iArrZzaa2);
                                        }
                                        break;
                                    case -1785516855:
                                        if (string2.equals("UPDATE")) {
                                            int[] iArrZzaa3 = zzaa(jSONObject.getJSONArray("itemIds"));
                                            Preconditions.checkNotNull(iArrZzaa3, "A list of item IDs is expected in a QUEUE UPDATE message.");
                                            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("reorderItemIds");
                                            if (jSONArrayOptJSONArray != null) {
                                                this.zzy.zzi(CastUtils.zzd(iArrZzaa3), CastUtils.zzd((int[]) Preconditions.checkNotNull(zzaa(jSONArrayOptJSONArray))), jSONObject.optInt("insertBefore", 0));
                                            } else {
                                                this.zzy.zze(iArrZzaa3);
                                            }
                                        }
                                        break;
                                    case 1122976047:
                                        if (string2.equals("ITEMS_CHANGE")) {
                                            this.zzy.zzj(iArrZzaa2);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case 431600379:
                    if (string.equals(MediaError.ERROR_TYPE_INVALID_PLAYER_STATE)) {
                        logger.w("received unexpected error: Invalid Player State.", new Object[0]);
                        Iterator it3 = zza().iterator();
                        while (it3.hasNext()) {
                            ((zzav) it3.next()).zzd(jOptLong, 2100, zzS(jSONObject));
                        }
                    }
                    break;
                case 823510221:
                    if (string.equals("MEDIA_STATUS")) {
                        JSONArray jSONArray2 = jSONObject.getJSONArray(NotificationCompat.CATEGORY_STATUS);
                        if (jSONArray2.length() > 0) {
                            JSONObject jSONObject2 = jSONArray2.getJSONObject(0);
                            boolean zZze = this.zzc.zze(jOptLong);
                            zzav zzavVar = this.zzh;
                            if (!zzavVar.zzf() || zzavVar.zze(jOptLong)) {
                                zzav zzavVar2 = this.zzi;
                                i = (!zzavVar2.zzf() || zzavVar2.zze(jOptLong)) ? 0 : 1;
                            }
                            if (zZze || (mediaStatus = this.zzw) == null) {
                                this.zzw = new MediaStatus(jSONObject2);
                                this.zzv = SystemClock.elapsedRealtime();
                                iZza = 127;
                            } else {
                                iZza = mediaStatus.zza(jSONObject2, i);
                            }
                            if ((iZza & 1) != 0) {
                                this.zzv = SystemClock.elapsedRealtime();
                                this.zzz = -1;
                                z = true;
                            } else {
                                z = false;
                            }
                            if ((iZza & 2) != 0) {
                                this.zzv = SystemClock.elapsedRealtime();
                                z = true;
                            }
                            if ((iZza & 128) != 0) {
                                this.zzv = SystemClock.elapsedRealtime();
                            }
                            if ((iZza & 4) != 0) {
                                zzV();
                            }
                            if ((iZza & 8) != 0) {
                                zzX();
                            }
                            if ((iZza & 16) != 0) {
                                zzW();
                            }
                            if ((iZza & 32) != 0) {
                                this.zzv = SystemClock.elapsedRealtime();
                                zzao zzaoVar = this.zzy;
                                if (zzaoVar != null) {
                                    zzaoVar.zza();
                                }
                            }
                            if ((iZza & 64) != 0) {
                                this.zzv = SystemClock.elapsedRealtime();
                            } else if (z) {
                            }
                            zzY();
                        } else {
                            this.zzw = null;
                            zzY();
                            zzV();
                            zzX();
                            zzW();
                        }
                        Iterator it4 = zza().iterator();
                        while (it4.hasNext()) {
                            ((zzav) it4.next()).zzd(jOptLong, 0, null);
                        }
                    }
                    break;
                case 2107149050:
                    if (string.equals("QUEUE_ITEM_IDS")) {
                        this.zzq.zzd(jOptLong, 0, null);
                        zzU(jSONObject, "QUEUE_ITEM_IDS");
                        if (this.zzy != null && (iArrZzaa = zzaa(jSONObject.getJSONArray("itemIds"))) != null) {
                            this.zzy.zze(iArrZzaa);
                            break;
                        }
                    }
                    break;
            }
        } catch (JSONException e) {
            this.zza.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    public final void zzP(long j, int i) {
        Iterator it = zza().iterator();
        while (it.hasNext()) {
            ((zzav) it.next()).zzd(j, i, null);
        }
    }

    public final void zzQ(zzao zzaoVar) {
        this.zzy = zzaoVar;
    }

    @Override // com.google.android.gms.cast.internal.zzq
    public final void zzf() {
        zzb();
        zzT();
    }

    public final long zzj() {
        MediaStatus mediaStatus;
        AdBreakStatus adBreakStatus;
        if (this.zzv == 0 || (mediaStatus = this.zzw) == null || (adBreakStatus = mediaStatus.getAdBreakStatus()) == null) {
            return 0L;
        }
        double playbackRate = mediaStatus.getPlaybackRate();
        if (playbackRate == 0.0d) {
            playbackRate = 1.0d;
        }
        return zzR(mediaStatus.getPlayerState() != 2 ? 0.0d : playbackRate, adBreakStatus.getCurrentBreakClipTimeInMs(), 0L);
    }

    public final long zzk() {
        MediaLiveSeekableRange liveSeekableRange;
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus == null || (liveSeekableRange = mediaStatus.getLiveSeekableRange()) == null) {
            return 0L;
        }
        long endTime = liveSeekableRange.getEndTime();
        return !liveSeekableRange.isLiveDone() ? zzR(1.0d, endTime, -1L) : endTime;
    }

    public final long zzl() {
        MediaLiveSeekableRange liveSeekableRange;
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus == null || (liveSeekableRange = mediaStatus.getLiveSeekableRange()) == null) {
            return 0L;
        }
        long startTime = liveSeekableRange.getStartTime();
        if (liveSeekableRange.isMovingWindow()) {
            startTime = zzR(1.0d, startTime, -1L);
        }
        return liveSeekableRange.isLiveDone() ? Math.min(startTime, liveSeekableRange.getEndTime()) : startTime;
    }

    public final long zzm() {
        MediaStatus mediaStatus;
        MediaInfo mediaInfoZzK = zzK();
        if (mediaInfoZzK == null || (mediaStatus = this.zzw) == null) {
            return 0L;
        }
        Long l = this.zzx;
        if (l == null) {
            if (this.zzv == 0) {
                return 0L;
            }
            double playbackRate = mediaStatus.getPlaybackRate();
            long streamPosition = mediaStatus.getStreamPosition();
            return (playbackRate == 0.0d || mediaStatus.getPlayerState() != 2) ? streamPosition : zzR(playbackRate, streamPosition, mediaInfoZzK.getStreamDuration());
        }
        if (l.equals(4294967296000L)) {
            if (this.zzw.getLiveSeekableRange() != null) {
                return Math.min(l.longValue(), zzk());
            }
            if (zzo() >= 0) {
                return Math.min(l.longValue(), zzo());
            }
        }
        return l.longValue();
    }

    public final long zzn() throws zzap {
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus != null) {
            return mediaStatus.zzb();
        }
        throw new zzap();
    }

    public final long zzo() {
        MediaInfo mediaInfoZzK = zzK();
        if (mediaInfoZzK != null) {
            return mediaInfoZzK.getStreamDuration();
        }
        return 0L;
    }

    public final long zzp(zzat zzatVar, MediaLoadRequestData mediaLoadRequestData) throws IllegalStateException, IllegalArgumentException {
        if (mediaLoadRequestData.getMediaInfo() == null && mediaLoadRequestData.getQueueData() == null) {
            throw new IllegalArgumentException("MediaInfo and MediaQueueData should not be both null");
        }
        JSONObject json = mediaLoadRequestData.toJson();
        if (json == null) {
            throw new IllegalArgumentException("Failed to jsonify the load request due to malformed request");
        }
        long jZzd = zzd();
        try {
            json.put("requestId", jZzd);
            json.put("type", "LOAD");
        } catch (JSONException unused) {
        }
        zzg(json.toString(), jZzd, null);
        this.zzc.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzq(zzat zzatVar, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "PAUSE");
            jSONObject2.put("mediaSessionId", zzn());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzd.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzr(zzat zzatVar, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "PLAY");
            jSONObject2.put("mediaSessionId", zzn());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zze.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzs(String str, List list) throws IllegalStateException {
        long jZzd = zzd();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "PRECACHE");
            jSONObject.put("precacheData", str);
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        return jZzd;
    }

    public final long zzt(zzat zzatVar, int i, int i2, int i3) throws IllegalArgumentException, zzap {
        if (i2 > 0 && i3 == 0) {
            i3 = 0;
        } else if (i2 != 0 || i3 <= 0) {
            throw new IllegalArgumentException("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "QUEUE_GET_ITEM_RANGE");
            jSONObject.put("mediaSessionId", zzn());
            jSONObject.put("itemId", i);
            if (i2 > 0) {
                jSONObject.put("nextCount", i2);
            }
            if (i3 > 0) {
                jSONObject.put("prevCount", i3);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzs.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzu(zzat zzatVar) throws IllegalStateException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", zzn());
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzq.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzv(zzat zzatVar, int[] iArr) throws IllegalArgumentException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i : iArr) {
                jSONArray.put(i);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzr.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzD(zzat zzatVar, long[] jArr) throws IllegalStateException, zzap {
        if (jArr == null) {
            throw new IllegalArgumentException("trackIds cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzk.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzH(zzat zzatVar, TextTrackStyle textTrackStyle) throws IllegalStateException, zzap {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put("requestId", jZzd);
            jSONObject.put("type", "EDIT_TRACKS_INFO");
            jSONObject.put("textTrackStyle", textTrackStyle.zza());
            jSONObject.put("mediaSessionId", zzn());
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzl.zzb(jZzd, zzatVar);
        return jZzd;
    }

    public final long zzy(zzat zzatVar, int[] iArr, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < iArr.length; i++) {
                jSONArray.put(i, iArr[i]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzZ()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzo.zzb(jZzd, new zzan(this, zzatVar));
        return jZzd;
    }

    public final long zzz(zzat zzatVar, int[] iArr, int i, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jSONArray.put(i2, iArr[i2]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzZ()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzp.zzb(jZzd, new zzan(this, zzatVar));
        return jZzd;
    }

    public final long zzw(zzat zzatVar, MediaQueueItem[] mediaQueueItemArr, int i, int i2, int i3, long j, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        }
        if (j != -1 && j < 0) {
            throw new IllegalArgumentException("playPosition can not be negative: " + j);
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "QUEUE_INSERT");
            jSONObject2.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i4 = 0; i4 < mediaQueueItemArr.length; i4++) {
                jSONArray.put(i4, mediaQueueItemArr[i4].toJson());
            }
            jSONObject2.put("items", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (i3 != -1) {
                jSONObject2.put("currentItemIndex", 0);
            }
            if (j != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzZ()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzm.zzb(jZzd, new zzan(this, zzatVar));
        return jZzd;
    }

    public final long zzA(zzat zzatVar, int i, long j, MediaQueueItem[] mediaQueueItemArr, int i2, Boolean bool, Integer num, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (j != -1 && j < 0) {
            throw new IllegalArgumentException("playPosition cannot be negative: " + j);
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "QUEUE_UPDATE");
            jSONObject2.put("mediaSessionId", zzn());
            if (i != 0) {
                jSONObject2.put("currentItemId", i);
            }
            if (i2 != 0) {
                jSONObject2.put("jump", i2);
            }
            if (mediaQueueItemArr != null && mediaQueueItemArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                    jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
                }
                jSONObject2.put("items", jSONArray);
            }
            if (bool != null) {
                jSONObject2.put("shuffle", bool);
            }
            String strZza = MediaCommon.zza(num);
            if (strZza != null) {
                jSONObject2.put("repeatMode", strZza);
            }
            if (j != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzZ()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzn.zzb(jZzd, new zzan(this, zzatVar));
        return jZzd;
    }

    public final long zzx(zzat zzatVar, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException {
        int length;
        if (mediaQueueItemArr == null || (length = mediaQueueItemArr.length) == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        }
        if (i < 0 || i >= length) {
            throw new IllegalArgumentException("Invalid startIndex: " + i);
        }
        if (j != -1 && j < 0) {
            throw new IllegalArgumentException("playPosition can not be negative: " + j);
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        this.zzc.zzb(jZzd, zzatVar);
        try {
            jSONObject2.put("requestId", jZzd);
            jSONObject2.put("type", "QUEUE_LOAD");
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
            }
            jSONObject2.put("items", jSONArray);
            String strZza = MediaCommon.zza(Integer.valueOf(i2));
            if (strZza == null) {
                throw new IllegalArgumentException("Invalid repeat mode: " + i2);
            }
            jSONObject2.put("repeatMode", strZza);
            jSONObject2.put("startIndex", i);
            if (j != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzZ()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
            zzg(jSONObject2.toString(), jZzd, null);
            return jZzd;
        } catch (JSONException unused) {
        }
    }
}
