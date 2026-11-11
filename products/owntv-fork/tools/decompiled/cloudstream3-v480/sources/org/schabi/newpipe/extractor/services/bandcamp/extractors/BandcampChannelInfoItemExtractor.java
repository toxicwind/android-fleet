package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import java.util.List;
import javax.annotation.Nonnull;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampChannelInfoItemExtractor implements ChannelInfoItemExtractor {
    private final Element resultInfo;
    private final Element searchResult;

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getStreamCount() {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public long getSubscriberCount() {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public boolean isVerified() throws ParsingException {
        return false;
    }

    public BandcampChannelInfoItemExtractor(Element element) {
        this.searchResult = element;
        this.resultInfo = element.getElementsByClass("result-info").first();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.resultInfo.getElementsByClass("heading").text();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return this.resultInfo.getElementsByClass("itemurl").text();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return BandcampExtractorHelper.getImagesFromSearchResult(this.searchResult);
    }

    @Override // org.schabi.newpipe.extractor.channel.ChannelInfoItemExtractor
    public String getDescription() {
        return this.resultInfo.getElementsByClass("subhead").text();
    }
}
