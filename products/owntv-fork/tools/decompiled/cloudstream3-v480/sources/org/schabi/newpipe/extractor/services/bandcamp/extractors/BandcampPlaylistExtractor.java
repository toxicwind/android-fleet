package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParserException;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Function$CC;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import javax.annotation.Nonnull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.PaidContentException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;
import org.schabi.newpipe.extractor.services.bandcamp.extractors.streaminfoitem.BandcampPlaylistStreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampPlaylistExtractor extends PlaylistExtractor {
    private static final int MAXIMUM_INDIVIDUAL_COVER_ARTS = 10;
    private JsonObject albumJson;
    private Document document;
    private String name;
    private JsonArray trackInfo;

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) {
        return null;
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return false;
    }

    public BandcampPlaylistExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler) {
        super(streamingService, listLinkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String strResponseBody = downloader.get(getLinkHandler().getUrl()).responseBody();
        this.document = Jsoup.parse(strResponseBody);
        JsonObject albumInfoJson = BandcampStreamExtractor.getAlbumInfoJson(strResponseBody);
        this.albumJson = albumInfoJson;
        this.trackInfo = albumInfoJson.getArray("trackinfo");
        try {
            this.name = JsonUtils.getJsonData(strResponseBody, "data-embed").getString("album_title");
            if (this.trackInfo.isEmpty()) {
                throw new PaidContentException("Album needs to be purchased");
            }
        } catch (JsonParserException e) {
            throw new ParsingException("Faulty JSON; page likely does not contain album data", e);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new ParsingException("JSON does not exist", e2);
        }
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        if (this.albumJson.isNull("art_id")) {
            return Collections.EMPTY_LIST;
        }
        return BandcampExtractorHelper.getImagesFromImageId(this.albumJson.getLong("art_id"), true);
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderUrl() throws ParsingException {
        return Utils.HTTPS + getUrl().split("/")[2] + "/";
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public String getUploaderName() {
        return this.albumJson.getString("artist");
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return BandcampExtractorHelper.getImagesFromImageUrl((String) Collection.EL.stream(this.document.getElementsByClass("band-photo")).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampPlaylistExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Element) obj).attr("src");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse(""));
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    public long getStreamCount() {
        return this.trackInfo.size();
    }

    @Override // org.schabi.newpipe.extractor.playlist.PlaylistExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        Element elementById = this.document.getElementById("trackInfo");
        if (elementById == null) {
            throw new ParsingException("Could not find trackInfo in document");
        }
        Elements elementsByClass = elementById.getElementsByClass("tralbum-about");
        Elements elementsByClass2 = elementById.getElementsByClass("tralbum-credits");
        Element elementById2 = this.document.getElementById("license");
        if (elementsByClass.isEmpty() && elementsByClass2.isEmpty() && elementById2 == null) {
            return Description.EMPTY_DESCRIPTION;
        }
        StringBuilder sb = new StringBuilder();
        if (!elementsByClass.isEmpty()) {
            sb.append(((Element) Objects.requireNonNull(elementsByClass.first())).html());
        }
        if (!elementsByClass2.isEmpty()) {
            sb.append(((Element) Objects.requireNonNull(elementsByClass2.first())).html());
        }
        if (elementById2 != null) {
            sb.append(elementById2.html());
        }
        return new Description(sb.toString(), 1);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws ExtractionException {
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        for (int i = 0; i < this.trackInfo.size(); i++) {
            JsonObject object = this.trackInfo.getObject(i);
            if (this.trackInfo.size() < 10) {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) new BandcampPlaylistStreamInfoItemExtractor(object, getUploaderUrl(), getService()));
            } else {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) new BandcampPlaylistStreamInfoItemExtractor(object, getUploaderUrl(), getThumbnails()));
            }
        }
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, null);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.name;
    }
}
