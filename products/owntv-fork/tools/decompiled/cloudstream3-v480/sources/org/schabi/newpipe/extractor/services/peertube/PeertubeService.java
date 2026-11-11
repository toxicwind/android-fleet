package org.schabi.newpipe.extractor.services.peertube;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.comments.CommentsExtractor;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.kiosk.KioskList;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeAccountExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeChannelExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeChannelTabExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeCommentsExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubePlaylistExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeSearchExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeStreamExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeSuggestionExtractor;
import org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeTrendingExtractor;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeCommentsLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubePlaylistLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeTrendingLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.subscription.SubscriptionExtractor;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeService extends StreamingService {
    private PeertubeInstance instance;

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SubscriptionExtractor getSubscriptionExtractor() {
        return null;
    }

    public PeertubeService(int i) {
        this(i, PeertubeInstance.DEFAULT_INSTANCE);
    }

    public PeertubeService(int i, PeertubeInstance peertubeInstance) {
        super(i, "PeerTube", EnumSet.of(StreamingService.ServiceInfo.MediaCapability.VIDEO, StreamingService.ServiceInfo.MediaCapability.COMMENTS));
        this.instance = peertubeInstance;
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public LinkHandlerFactory getStreamLHFactory() {
        return PeertubeStreamLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getChannelLHFactory() {
        return PeertubeChannelLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getChannelTabLHFactory() {
        return PeertubeChannelTabLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getPlaylistLHFactory() {
        return PeertubePlaylistLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SearchQueryHandlerFactory getSearchQHFactory() {
        return PeertubeSearchQueryHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getCommentsLHFactory() {
        return PeertubeCommentsLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SearchExtractor getSearchExtractor(SearchQueryHandler searchQueryHandler) {
        List<String> contentFilters = searchQueryHandler.getContentFilters();
        boolean z = false;
        if (!contentFilters.isEmpty() && contentFilters.get(0).startsWith("sepia_")) {
            z = true;
        }
        return new PeertubeSearchExtractor(this, searchQueryHandler, z);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SuggestionExtractor getSuggestionExtractor() {
        return new PeertubeSuggestionExtractor(this);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ChannelExtractor getChannelExtractor(ListLinkHandler listLinkHandler) throws ExtractionException {
        if (listLinkHandler.getUrl().contains("/video-channels/")) {
            return new PeertubeChannelExtractor(this, listLinkHandler);
        }
        return new PeertubeAccountExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ChannelTabExtractor getChannelTabExtractor(ListLinkHandler listLinkHandler) throws ExtractionException {
        return new PeertubeChannelTabExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public PlaylistExtractor getPlaylistExtractor(ListLinkHandler listLinkHandler) throws ExtractionException {
        return new PeertubePlaylistExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public StreamExtractor getStreamExtractor(LinkHandler linkHandler) throws ExtractionException {
        return new PeertubeStreamExtractor(this, linkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public CommentsExtractor getCommentsExtractor(ListLinkHandler listLinkHandler) throws ExtractionException {
        return new PeertubeCommentsExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public String getBaseUrl() {
        return this.instance.getUrl();
    }

    public PeertubeInstance getInstance() {
        return this.instance;
    }

    public void setInstance(PeertubeInstance peertubeInstance) {
        this.instance = peertubeInstance;
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public KioskList getKioskList() throws ExtractionException {
        final PeertubeTrendingLinkHandlerFactory peertubeTrendingLinkHandlerFactory = PeertubeTrendingLinkHandlerFactory.getInstance();
        KioskList.KioskExtractorFactory kioskExtractorFactory = new KioskList.KioskExtractorFactory() { // from class: org.schabi.newpipe.extractor.services.peertube.PeertubeService$$ExternalSyntheticLambda0
            @Override // org.schabi.newpipe.extractor.kiosk.KioskList.KioskExtractorFactory
            public final KioskExtractor createNewKiosk(StreamingService streamingService, String str, String str2) {
                return this.f$0.m10964x4edeae45(peertubeTrendingLinkHandlerFactory, streamingService, str, str2);
            }
        };
        KioskList kioskList = new KioskList(this);
        try {
            kioskList.addKioskEntry(kioskExtractorFactory, peertubeTrendingLinkHandlerFactory, "Trending");
            kioskList.addKioskEntry(kioskExtractorFactory, peertubeTrendingLinkHandlerFactory, PeertubeTrendingLinkHandlerFactory.KIOSK_MOST_LIKED);
            kioskList.addKioskEntry(kioskExtractorFactory, peertubeTrendingLinkHandlerFactory, PeertubeTrendingLinkHandlerFactory.KIOSK_RECENT);
            kioskList.addKioskEntry(kioskExtractorFactory, peertubeTrendingLinkHandlerFactory, PeertubeTrendingLinkHandlerFactory.KIOSK_LOCAL);
            kioskList.setDefaultKiosk("Trending");
            return kioskList;
        } catch (Exception e) {
            throw new ExtractionException(e);
        }
    }

    /* JADX INFO: renamed from: lambda$getKioskList$0$org-schabi-newpipe-extractor-services-peertube-PeertubeService, reason: not valid java name */
    /* synthetic */ KioskExtractor m10964x4edeae45(PeertubeTrendingLinkHandlerFactory peertubeTrendingLinkHandlerFactory, StreamingService streamingService, String str, String str2) throws IOException, ExtractionException {
        return new PeertubeTrendingExtractor(this, peertubeTrendingLinkHandlerFactory.fromId(str2), str2);
    }
}
