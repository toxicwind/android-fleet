package org.schabi.newpipe.extractor;

import java.util.List;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.services.bandcamp.BandcampService;
import org.schabi.newpipe.extractor.services.media_ccc.MediaCCCService;
import org.schabi.newpipe.extractor.services.peertube.PeertubeService;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudService;
import org.schabi.newpipe.extractor.services.youtube.YoutubeService;

/* JADX INFO: loaded from: classes8.dex */
public final class ServiceList {
    public static final BandcampService Bandcamp;
    public static final MediaCCCService MediaCCC;
    public static final PeertubeService PeerTube;
    private static final List<StreamingService> SERVICES;
    public static final SoundcloudService SoundCloud;
    public static final YoutubeService YouTube;

    private ServiceList() {
    }

    static {
        YoutubeService youtubeService = new YoutubeService(0);
        YouTube = youtubeService;
        SoundcloudService soundcloudService = new SoundcloudService(1);
        SoundCloud = soundcloudService;
        MediaCCCService mediaCCCService = new MediaCCCService(2);
        MediaCCC = mediaCCCService;
        PeertubeService peertubeService = new PeertubeService(3);
        PeerTube = peertubeService;
        BandcampService bandcampService = new BandcampService(4);
        Bandcamp = bandcampService;
        SERVICES = UByte$$ExternalSyntheticBackport0.m(new Object[]{youtubeService, soundcloudService, mediaCCCService, peertubeService, bandcampService});
    }

    public static List<StreamingService> all() {
        return SERVICES;
    }
}
