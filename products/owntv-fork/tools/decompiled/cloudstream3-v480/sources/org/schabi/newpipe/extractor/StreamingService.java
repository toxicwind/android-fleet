package org.schabi.newpipe.extractor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.channel.ChannelExtractor;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabExtractor;
import org.schabi.newpipe.extractor.comments.CommentsExtractor;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.feed.FeedExtractor;
import org.schabi.newpipe.extractor.kiosk.KioskList;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.localization.TimeAgoParser;
import org.schabi.newpipe.extractor.localization.TimeAgoPatternsManager;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.subscription.SubscriptionExtractor;
import org.schabi.newpipe.extractor.suggestion.SuggestionExtractor;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public abstract class StreamingService {
    private final int serviceId;
    private final ServiceInfo serviceInfo;

    public enum LinkType {
        NONE,
        STREAM,
        CHANNEL,
        PLAYLIST
    }

    public abstract String getBaseUrl();

    public abstract ChannelExtractor getChannelExtractor(ListLinkHandler listLinkHandler) throws ExtractionException;

    public abstract ListLinkHandlerFactory getChannelLHFactory();

    public abstract ChannelTabExtractor getChannelTabExtractor(ListLinkHandler listLinkHandler) throws ExtractionException;

    public abstract ListLinkHandlerFactory getChannelTabLHFactory();

    public abstract CommentsExtractor getCommentsExtractor(ListLinkHandler listLinkHandler) throws ExtractionException;

    public abstract ListLinkHandlerFactory getCommentsLHFactory();

    @Nullable
    public FeedExtractor getFeedExtractor(String str) throws ExtractionException {
        return null;
    }

    public abstract KioskList getKioskList() throws ExtractionException;

    public abstract PlaylistExtractor getPlaylistExtractor(ListLinkHandler listLinkHandler) throws ExtractionException;

    public abstract ListLinkHandlerFactory getPlaylistLHFactory();

    public abstract SearchExtractor getSearchExtractor(SearchQueryHandler searchQueryHandler);

    public abstract SearchQueryHandlerFactory getSearchQHFactory();

    public abstract StreamExtractor getStreamExtractor(LinkHandler linkHandler) throws ExtractionException;

    public abstract LinkHandlerFactory getStreamLHFactory();

    public abstract SubscriptionExtractor getSubscriptionExtractor();

    public abstract SuggestionExtractor getSuggestionExtractor();

    public static class ServiceInfo {
        private final Set<MediaCapability> mediaCapabilities;
        private final String name;

        public enum MediaCapability {
            AUDIO,
            VIDEO,
            LIVE,
            COMMENTS
        }

        public ServiceInfo(String str, Set<MediaCapability> set) {
            this.name = str;
            this.mediaCapabilities = set;
        }

        public String getName() {
            return this.name;
        }

        public Set<MediaCapability> getMediaCapabilities() {
            return this.mediaCapabilities;
        }
    }

    public StreamingService(int i, String str, Set<ServiceInfo.MediaCapability> set) {
        this.serviceId = i;
        this.serviceInfo = new ServiceInfo(str, set);
    }

    public final int getServiceId() {
        return this.serviceId;
    }

    public ServiceInfo getServiceInfo() {
        return this.serviceInfo;
    }

    public String toString() {
        return this.serviceId + ":" + this.serviceInfo.getName();
    }

    public SearchExtractor getSearchExtractor(String str, List<String> list, String str2) throws ExtractionException {
        return getSearchExtractor(getSearchQHFactory().fromQuery(str, list, str2));
    }

    public ChannelExtractor getChannelExtractor(String str, List<String> list, String str2) throws ExtractionException {
        return getChannelExtractor(getChannelLHFactory().fromQuery(str, list, str2));
    }

    public PlaylistExtractor getPlaylistExtractor(String str, List<String> list, String str2) throws ExtractionException {
        return getPlaylistExtractor(getPlaylistLHFactory().fromQuery(str, list, str2));
    }

    public SearchExtractor getSearchExtractor(String str) throws ExtractionException {
        return getSearchExtractor(getSearchQHFactory().fromQuery(str));
    }

    public ChannelExtractor getChannelExtractor(String str) throws ExtractionException {
        return getChannelExtractor(getChannelLHFactory().fromUrl(str));
    }

    public ChannelTabExtractor getChannelTabExtractorFromId(String str, String str2) throws ExtractionException {
        return getChannelTabExtractor(getChannelTabLHFactory().fromQuery(str, Collections.singletonList(str2), ""));
    }

    public ChannelTabExtractor getChannelTabExtractorFromIdAndBaseUrl(String str, String str2, String str3) throws ExtractionException {
        return getChannelTabExtractor(getChannelTabLHFactory().fromQuery(str, Collections.singletonList(str2), "", str3));
    }

    public PlaylistExtractor getPlaylistExtractor(String str) throws ExtractionException {
        return getPlaylistExtractor(getPlaylistLHFactory().fromUrl(str));
    }

    public StreamExtractor getStreamExtractor(String str) throws ExtractionException {
        return getStreamExtractor(getStreamLHFactory().fromUrl(str));
    }

    public CommentsExtractor getCommentsExtractor(String str) throws ExtractionException {
        ListLinkHandlerFactory commentsLHFactory = getCommentsLHFactory();
        if (commentsLHFactory == null) {
            return null;
        }
        return getCommentsExtractor(commentsLHFactory.fromUrl(str));
    }

    public final LinkType getLinkTypeByUrl(String str) throws ParsingException {
        String strFollowGoogleRedirectIfNeeded = Utils.followGoogleRedirectIfNeeded(str);
        LinkHandlerFactory streamLHFactory = getStreamLHFactory();
        ListLinkHandlerFactory channelLHFactory = getChannelLHFactory();
        ListLinkHandlerFactory playlistLHFactory = getPlaylistLHFactory();
        if (streamLHFactory != null && streamLHFactory.acceptUrl(strFollowGoogleRedirectIfNeeded)) {
            return LinkType.STREAM;
        }
        if (channelLHFactory != null && channelLHFactory.acceptUrl(strFollowGoogleRedirectIfNeeded)) {
            return LinkType.CHANNEL;
        }
        if (playlistLHFactory != null && playlistLHFactory.acceptUrl(strFollowGoogleRedirectIfNeeded)) {
            return LinkType.PLAYLIST;
        }
        return LinkType.NONE;
    }

    public List<Localization> getSupportedLocalizations() {
        return Collections.singletonList(Localization.DEFAULT);
    }

    public List<ContentCountry> getSupportedCountries() {
        return Collections.singletonList(ContentCountry.DEFAULT);
    }

    public Localization getLocalization() {
        Localization preferredLocalization = NewPipe.getPreferredLocalization();
        if (getSupportedLocalizations().contains(preferredLocalization)) {
            return preferredLocalization;
        }
        for (Localization localization : getSupportedLocalizations()) {
            if (localization.getLanguageCode().equals(preferredLocalization.getLanguageCode())) {
                return localization;
            }
        }
        return Localization.DEFAULT;
    }

    public ContentCountry getContentCountry() {
        ContentCountry preferredContentCountry = NewPipe.getPreferredContentCountry();
        return getSupportedCountries().contains(preferredContentCountry) ? preferredContentCountry : ContentCountry.DEFAULT;
    }

    public TimeAgoParser getTimeAgoParser(Localization localization) {
        TimeAgoParser timeAgoParserFor;
        TimeAgoParser timeAgoParserFor2 = TimeAgoPatternsManager.getTimeAgoParserFor(localization);
        if (timeAgoParserFor2 != null) {
            return timeAgoParserFor2;
        }
        if (!localization.getCountryCode().isEmpty() && (timeAgoParserFor = TimeAgoPatternsManager.getTimeAgoParserFor(new Localization(localization.getLanguageCode()))) != null) {
            return timeAgoParserFor;
        }
        throw new IllegalArgumentException("Localization is not supported (\"" + localization + "\")");
    }
}
