package com.google.android.gms.cast.framework.media.uicontroller;

import android.text.format.DateUtils;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.text.DateFormat;
import java.util.Date;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zza {
    RemoteMediaClient zza;

    private zza() {
    }

    public static zza zzf() {
        return new zza();
    }

    private final MediaMetadata zzo() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (mediaInfo = this.zza.getMediaInfo()) == null) {
            return null;
        }
        return mediaInfo.getMetadata();
    }

    private static final String zzp(long j) {
        return j >= 0 ? DateUtils.formatElapsedTime(j / 1000) : "-".concat(String.valueOf(DateUtils.formatElapsedTime((-j) / 1000)));
    }

    public final int zza() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            RemoteMediaClient remoteMediaClient2 = this.zza;
            if (remoteMediaClient2.isLiveStream() || !remoteMediaClient2.isLoadingNextItem()) {
                int approximateStreamPosition = (int) (remoteMediaClient2.getApproximateStreamPosition() - zze());
                if (remoteMediaClient2.zzw()) {
                    int iZzd = zzd();
                    int iZzc = zzc();
                    int i = CastUtils.zza;
                    approximateStreamPosition = Math.min(Math.max(approximateStreamPosition, iZzd), iZzc);
                }
                int iZzb = zzb();
                int i2 = CastUtils.zza;
                return Math.min(Math.max(approximateStreamPosition, 0), iZzb);
            }
        }
        return 0;
    }

    public final int zzb() {
        MediaInfo media;
        RemoteMediaClient remoteMediaClient = this.zza;
        long jLongValue = 1;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            RemoteMediaClient remoteMediaClient2 = this.zza;
            if (remoteMediaClient2.isLiveStream()) {
                Long lZzi = zzi();
                if (lZzi != null) {
                    jLongValue = lZzi.longValue();
                } else {
                    Long lZzg = zzg();
                    jLongValue = lZzg != null ? lZzg.longValue() : Math.max(remoteMediaClient2.getApproximateStreamPosition(), 1L);
                }
            } else if (remoteMediaClient2.isLoadingNextItem()) {
                MediaQueueItem loadingItem = remoteMediaClient2.getLoadingItem();
                if (loadingItem != null && (media = loadingItem.getMedia()) != null) {
                    jLongValue = Math.max(media.getStreamDuration(), 1L);
                }
            } else {
                jLongValue = Math.max(remoteMediaClient2.getStreamDuration(), 1L);
            }
        }
        return Math.max((int) (jLongValue - zze()), 1);
    }

    public final int zzc() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return zzb();
        }
        if (!this.zza.zzw()) {
            return 0;
        }
        long jLongValue = ((Long) Preconditions.checkNotNull(zzg())).longValue() - zze();
        int iZzb = zzb();
        int i = CastUtils.zza;
        return Math.min(Math.max((int) jLongValue, 0), iZzb);
    }

    public final int zzd() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzw()) {
            return 0;
        }
        long jLongValue = ((Long) Preconditions.checkNotNull(zzh())).longValue() - zze();
        int iZzb = zzb();
        int i = CastUtils.zza;
        return Math.min(Math.max((int) jLongValue, 0), iZzb);
    }

    public final long zze() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return 0L;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        Long lZzj = zzj();
        if (lZzj != null) {
            return lZzj.longValue();
        }
        Long lZzh = zzh();
        return lZzh != null ? lZzh.longValue() : remoteMediaClient2.getApproximateStreamPosition();
    }

    final Long zzg() {
        RemoteMediaClient remoteMediaClient;
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzw() || (mediaStatus = (remoteMediaClient = this.zza).getMediaStatus()) == null || mediaStatus.getLiveSeekableRange() == null) {
            return null;
        }
        return Long.valueOf(remoteMediaClient.getApproximateLiveSeekableRangeEnd());
    }

    final Long zzh() {
        RemoteMediaClient remoteMediaClient;
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzw() || (mediaStatus = (remoteMediaClient = this.zza).getMediaStatus()) == null || mediaStatus.getLiveSeekableRange() == null) {
            return null;
        }
        return Long.valueOf(remoteMediaClient.getApproximateLiveSeekableRangeStart());
    }

    public final Long zzi() {
        MediaMetadata mediaMetadataZzo;
        Long lZzj;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || (mediaMetadataZzo = zzo()) == null || !mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_DURATION) || (lZzj = zzj()) == null) {
            return null;
        }
        return Long.valueOf(lZzj.longValue() + mediaMetadataZzo.getTimeMillis(MediaMetadata.KEY_SECTION_DURATION));
    }

    public final Long zzj() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return null;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        MediaInfo mediaInfo = remoteMediaClient2.getMediaInfo();
        MediaMetadata mediaMetadataZzo = zzo();
        if (mediaInfo == null || mediaMetadataZzo == null || !mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA)) {
            return null;
        }
        if (mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_DURATION) || remoteMediaClient2.zzw()) {
            return Long.valueOf(mediaMetadataZzo.getTimeMillis(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA));
        }
        return null;
    }

    final Long zzk() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || (mediaInfo = this.zza.getMediaInfo()) == null || mediaInfo.getStartAbsoluteTime() == -1) {
            return null;
        }
        return Long.valueOf(mediaInfo.getStartAbsoluteTime());
    }

    public final String zzl(long j) {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return null;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        int i = 1;
        if (remoteMediaClient2 != null && remoteMediaClient2.hasMediaSession() && this.zza.isLiveStream() && zzk() != null) {
            i = 2;
        }
        if (i - 1 != 0) {
            return DateFormat.getTimeInstance().format(new Date(((Long) Preconditions.checkNotNull(zzk())).longValue() + j));
        }
        return (remoteMediaClient2.isLiveStream() && zzj() == null) ? zzp(j) : zzp(j - zze());
    }

    public final boolean zzm() {
        return zzn(((long) zza()) + zze());
    }

    public final boolean zzn(long j) {
        RemoteMediaClient remoteMediaClient = this.zza;
        return remoteMediaClient != null && remoteMediaClient.hasMediaSession() && this.zza.zzw() && (((long) zzc()) + zze()) - j < 10000;
    }
}
