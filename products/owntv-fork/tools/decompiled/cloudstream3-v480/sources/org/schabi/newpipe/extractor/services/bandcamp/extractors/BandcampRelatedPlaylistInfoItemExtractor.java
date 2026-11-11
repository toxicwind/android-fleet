package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import java.util.List;
import javax.annotation.Nonnull;
import org.jsoup.nodes.Element;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.playlist.PlaylistInfo;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.Description;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampRelatedPlaylistInfoItemExtractor implements PlaylistInfoItemExtractor {
    private final Element relatedAlbum;

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ Description getDescription() {
        return Description.EMPTY_DESCRIPTION;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public /* synthetic */ PlaylistInfo.PlaylistType getPlaylistType() {
        return PlaylistInfo.PlaylistType.NORMAL;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public long getStreamCount() throws ParsingException {
        return -1L;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderUrl() throws ParsingException {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public BandcampRelatedPlaylistInfoItemExtractor(@Nonnull Element element) {
        this.relatedAlbum = element;
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getName() throws ParsingException {
        return this.relatedAlbum.getElementsByClass("release-title").text();
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    public String getUrl() throws ParsingException {
        return this.relatedAlbum.getElementsByClass("album-link").attr("abs:href");
    }

    @Override // org.schabi.newpipe.extractor.InfoItemExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return BandcampExtractorHelper.getImagesFromImageUrl(this.relatedAlbum.getElementsByClass("album-art").attr("src"));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistInfoItemExtractor
    public String getUploaderName() throws ParsingException {
        return this.relatedAlbum.getElementsByClass("by-artist").text().replace("by ", "");
    }
}
