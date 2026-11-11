package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import coil3.util.UtilsKt;
import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParserException;
import j$.util.Collection;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements$$ExternalSyntheticLambda1;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.PaidContentException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.stream.VideoStream;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampStreamExtractor extends StreamExtractor {
    private JsonObject albumJson;
    private JsonObject current;
    private Document document;

    public BandcampStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String strResponseBody = downloader.get(getLinkHandler().getUrl()).responseBody();
        this.document = Jsoup.parse(strResponseBody);
        JsonObject albumInfoJson = getAlbumInfoJson(strResponseBody);
        this.albumJson = albumInfoJson;
        this.current = albumInfoJson.getObject("current");
        if (this.albumJson.getArray("trackinfo").size() > 1) {
            throw new ExtractionException("Page is actually an album, not a track");
        }
        if (this.albumJson.getArray("trackinfo").getObject(0).isNull(UtilsKt.SCHEME_FILE)) {
            throw new PaidContentException("This track is not available without being purchased");
        }
    }

    public static JsonObject getAlbumInfoJson(String str) throws ParsingException {
        try {
            return JsonUtils.getJsonData(str, "data-tralbum");
        } catch (JsonParserException e) {
            throw new ParsingException("Faulty JSON; page likely does not contain album data", e);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new ParsingException("JSON does not exist", e2);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.current.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() throws ParsingException {
        return Utils.HTTPS + getUrl().split("/")[2] + "/";
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getUrl() throws ParsingException {
        return Utils.replaceHttpWithHttps(this.albumJson.getString(ImagesContract.URL));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() throws ParsingException {
        return this.albumJson.getString("artist");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public String getTextualUploadDate() {
        return this.current.getString("publish_date");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return BandcampExtractorHelper.parseDate(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        if (this.albumJson.isNull("art_id")) {
            return Collections.EMPTY_LIST;
        }
        return BandcampExtractorHelper.getImagesFromImageId(this.albumJson.getLong("art_id"), true);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return BandcampExtractorHelper.getImagesFromImageUrl((String) Collection.EL.stream(this.document.getElementsByClass("band-photo")).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor$$ExternalSyntheticLambda0
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

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() {
        return new Description(Utils.nonEmptyAndNullJoin("\n\n", this.current.getString("about"), this.current.getString("lyrics"), this.current.getString("credits")), 3);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() {
        return Collections.singletonList(new AudioStream.Builder().setId("mp3-128").setContent(this.albumJson.getArray("trackinfo").getObject(0).getObject(UtilsKt.SCHEME_FILE).getString("mp3-128"), true).setMediaFormat(MediaFormat.MP3).setAverageBitrate(128).build());
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLength() throws ParsingException {
        return (long) this.albumJson.getArray("trackinfo").getObject(0).getDouble(TypedValues.TransitionType.S_DURATION);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoStreams() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoOnlyStreams() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public StreamType getStreamType() {
        return StreamType.AUDIO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public PlaylistInfoItemsCollector getRelatedItems() {
        final PlaylistInfoItemsCollector playlistInfoItemsCollector = new PlaylistInfoItemsCollector(getServiceId());
        Collection.EL.stream(this.document.getElementsByClass("recommended-album")).map(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new BandcampRelatedPlaylistInfoItemExtractor((Element) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).forEach(new Consumer() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                playlistInfoItemsCollector.commit((BandcampRelatedPlaylistInfoItemExtractor) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        return playlistInfoItemsCollector;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getCategory() {
        return (String) Collection.EL.stream(this.document.getElementsByClass("tralbum-tags")).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Collection.EL.stream(((Element) obj).getElementsByClass("tag"));
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).map(new Elements$$ExternalSyntheticLambda1()).findFirst().orElse("");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getLicence() {
        switch (this.current.getInt("license_type")) {
            case 1:
                return "All rights reserved ©";
            case 2:
                return "CC BY-NC-ND 3.0";
            case 3:
                return "CC BY-NC-SA 3.0";
            case 4:
                return "CC BY-NC 3.0";
            case 5:
                return "CC BY-ND 3.0";
            case 6:
                return "CC BY 3.0";
            case 7:
            default:
                return "Unknown";
            case 8:
                return "CC BY-SA 3.0";
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<String> getTags() {
        return (List) Collection.EL.stream(this.document.getElementsByAttributeValue("itemprop", "keywords")).map(new Elements$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }
}
