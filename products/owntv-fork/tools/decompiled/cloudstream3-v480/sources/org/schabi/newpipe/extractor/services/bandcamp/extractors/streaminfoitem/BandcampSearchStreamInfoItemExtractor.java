package org.schabi.newpipe.extractor.services.bandcamp.extractors.streaminfoitem;

import java.util.List;
import javax.annotation.Nonnull;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampExtractorHelper;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampSearchStreamInfoItemExtractor extends BandcampStreamInfoItemExtractor {
    private final Element resultInfo;
    private final Element searchResult;

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public long getDuration() {
        return -1L;
    }

    public BandcampSearchStreamInfoItemExtractor(Element element, String str) {
        super(str);
        this.searchResult = element;
        this.resultInfo = element.getElementsByClass("result-info").first();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
    public String getUploaderName() {
        String[] strArrSplit = this.resultInfo.getElementsByClass("subhead").text().split("by ");
        if (strArrSplit.length > 1) {
            return strArrSplit[1];
        }
        return strArrSplit[0];
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
}
