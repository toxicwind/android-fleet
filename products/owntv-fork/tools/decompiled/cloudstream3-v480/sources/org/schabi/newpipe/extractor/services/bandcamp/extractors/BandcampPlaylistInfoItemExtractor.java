package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import java.util.List;
import javax.annotation.Nonnull;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampPlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private final Element resultInfo;
    private final Element searchResult;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() {
        return false;
    }

    public BandcampPlaylistInfoItemExtractor(@Nonnull Element element) {
        this.searchResult = element;
        this.resultInfo = element.getElementsByClass("result-info").first();
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() {
        return this.resultInfo.getElementsByClass("subhead").text().split(" by")[0];
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() {
        return Integer.parseInt(this.resultInfo.getElementsByClass("length").text().split(" track")[0]);
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() {
        return this.resultInfo.getElementsByClass("heading").text();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() {
        return this.resultInfo.getElementsByClass("itemurl").text();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        return BandcampExtractorHelper.getImagesFromSearchResult(this.searchResult);
    }
}
