package org.schabi.newpipe.extractor.services.soundcloud;

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
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudChannelExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudChannelTabExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudChartsExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudCommentsExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudPlaylistExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudSearchExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudStreamExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudSubscriptionExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudSuggestionExtractor;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudChannelTabLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudChartsLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudCommentsLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudPlaylistLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.subscription.SubscriptionExtractor;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudService extends StreamingService {
    public SoundcloudService(int i) {
        super(i, "SoundCloud", EnumSet.of(StreamingService.ServiceInfo.MediaCapability.AUDIO, StreamingService.ServiceInfo.MediaCapability.COMMENTS));
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public String getBaseUrl() {
        return "https://soundcloud.com";
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SearchQueryHandlerFactory getSearchQHFactory() {
        return SoundcloudSearchQueryHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public LinkHandlerFactory getStreamLHFactory() {
        return SoundcloudStreamLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getChannelLHFactory() {
        return SoundcloudChannelLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getChannelTabLHFactory() {
        return SoundcloudChannelTabLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getPlaylistLHFactory() {
        return SoundcloudPlaylistLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public List<ContentCountry> getSupportedCountries() {
        return ContentCountry.listFrom("AU", "CA", "DE", "FR", "GB", "IE", "NL", "NZ", "US");
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public StreamExtractor getStreamExtractor(LinkHandler linkHandler) {
        return new SoundcloudStreamExtractor(this, linkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ChannelExtractor getChannelExtractor(ListLinkHandler listLinkHandler) {
        return new SoundcloudChannelExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ChannelTabExtractor getChannelTabExtractor(ListLinkHandler listLinkHandler) {
        return new SoundcloudChannelTabExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public PlaylistExtractor getPlaylistExtractor(ListLinkHandler listLinkHandler) {
        return new SoundcloudPlaylistExtractor(this, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SearchExtractor getSearchExtractor(SearchQueryHandler searchQueryHandler) {
        return new SoundcloudSearchExtractor(this, searchQueryHandler);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SoundcloudSuggestionExtractor getSuggestionExtractor() {
        return new SoundcloudSuggestionExtractor(this);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public KioskList getKioskList() throws ExtractionException {
        KioskList kioskList = new KioskList(this);
        final SoundcloudChartsLinkHandlerFactory soundcloudChartsLinkHandlerFactory = SoundcloudChartsLinkHandlerFactory.getInstance();
        try {
            kioskList.addKioskEntry(new KioskList.KioskExtractorFactory() { // from class: org.schabi.newpipe.extractor.services.soundcloud.SoundcloudService$$ExternalSyntheticLambda0
                @Override // org.schabi.newpipe.extractor.kiosk.KioskList.KioskExtractorFactory
                public final KioskExtractor createNewKiosk(StreamingService streamingService, String str, String str2) {
                    return this.f$0.m10965x91ee185(soundcloudChartsLinkHandlerFactory, streamingService, str, str2);
                }
            }, soundcloudChartsLinkHandlerFactory, "New & hot");
            kioskList.setDefaultKiosk("New & hot");
            return kioskList;
        } catch (Exception e) {
            throw new ExtractionException(e);
        }
    }

    /* JADX INFO: renamed from: lambda$getKioskList$0$org-schabi-newpipe-extractor-services-soundcloud-SoundcloudService, reason: not valid java name */
    /* synthetic */ KioskExtractor m10965x91ee185(SoundcloudChartsLinkHandlerFactory soundcloudChartsLinkHandlerFactory, StreamingService streamingService, String str, String str2) throws IOException, ExtractionException {
        return new SoundcloudChartsExtractor(this, soundcloudChartsLinkHandlerFactory.fromUrl(str), str2);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public SubscriptionExtractor getSubscriptionExtractor() {
        return new SoundcloudSubscriptionExtractor(this);
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public ListLinkHandlerFactory getCommentsLHFactory() {
        return SoundcloudCommentsLinkHandlerFactory.getInstance();
    }

    @Override // org.schabi.newpipe.extractor.StreamingService
    public CommentsExtractor getCommentsExtractor(ListLinkHandler listLinkHandler) throws ExtractionException {
        return new SoundcloudCommentsExtractor(this, listLinkHandler);
    }
}
