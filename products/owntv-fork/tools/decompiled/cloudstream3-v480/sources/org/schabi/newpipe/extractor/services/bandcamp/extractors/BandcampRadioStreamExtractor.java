package org.schabi.newpipe.extractor.services.bandcamp.extractors;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.cast.MediaTrack;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements$$ExternalSyntheticLambda1;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ContentNotSupportedException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.StreamSegment;

/* JADX INFO: loaded from: classes8.dex */
public class BandcampRadioStreamExtractor extends BandcampStreamExtractor {
    private static final String MP3_128 = "mp3-128";
    private static final String OPUS_LO = "opus-lo";
    private JsonObject showInfo;

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    public PlaylistInfoItemsCollector getRelatedItems() {
        return null;
    }

    public BandcampRadioStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
    }

    static JsonObject query(int i) throws ParsingException {
        try {
            return JsonParser.object().from(NewPipe.getDownloader().get("https://bandcamp.com/api/bcweekly/1/get?id=" + i).responseBody());
        } catch (JsonParserException | IOException | ReCaptchaException e) {
            throw new ParsingException("could not get show data", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        this.showInfo = query(Integer.parseInt(getId()));
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.showInfo.getString(MediaTrack.ROLE_SUBTITLE);
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() throws ContentNotSupportedException {
        throw new ContentNotSupportedException("Fan pages are not supported");
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getUrl() throws ParsingException {
        return getLinkHandler().getUrl();
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() throws ParsingException {
        return (String) Collection.EL.stream(Jsoup.parse(this.showInfo.getString("image_caption")).getElementsByTag(CmcdData.OBJECT_TYPE_AUDIO_ONLY)).map(new Elements$$ExternalSyntheticLambda1()).findFirst().orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampRadioStreamExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return BandcampRadioStreamExtractor.lambda$getUploaderName$0();
            }
        });
    }

    static /* synthetic */ ParsingException lambda$getUploaderName$0() {
        return new ParsingException("Could not get uploader name");
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public String getTextualUploadDate() {
        return this.showInfo.getString("published_date");
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return BandcampExtractorHelper.getImagesFromImageId(this.showInfo.getLong("show_image_id"), false);
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return Collections.singletonList(new Image("https://bandcamp.com/img/buttons/bandcamp-button-circle-whitecolor-512.png", 512, 512, Image.ResolutionLevel.MEDIUM));
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() {
        return new Description(this.showInfo.getString("desc"), 3);
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLength() {
        return this.showInfo.getLong("audio_duration");
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() {
        ArrayList arrayList = new ArrayList();
        JsonObject object = this.showInfo.getObject("audio_stream");
        if (object.has(MP3_128)) {
            arrayList.add(new AudioStream.Builder().setId(MP3_128).setContent(object.getString(MP3_128), true).setMediaFormat(MediaFormat.MP3).setAverageBitrate(128).build());
        }
        if (object.has(OPUS_LO)) {
            arrayList.add(new AudioStream.Builder().setId(OPUS_LO).setContent(object.getString(OPUS_LO), true).setMediaFormat(MediaFormat.OPUS).setAverageBitrate(100).build());
        }
        return arrayList;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<StreamSegment> getStreamSegments() throws ParsingException {
        JsonArray<JsonObject> array = this.showInfo.getArray("tracks");
        ArrayList arrayList = new ArrayList(array.size());
        for (JsonObject jsonObject : array) {
            StreamSegment streamSegment = new StreamSegment(jsonObject.getString("title"), jsonObject.getInt("timecode"));
            streamSegment.setPreviewUrl(BandcampExtractorHelper.getImageUrl(jsonObject.getLong("track_art_id"), true));
            streamSegment.setChannelName(jsonObject.getString("artist"));
            arrayList.add(streamSegment);
        }
        return arrayList;
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getLicence() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getCategory() {
        return "";
    }

    @Override // org.schabi.newpipe.extractor.services.bandcamp.extractors.BandcampStreamExtractor, org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<String> getTags() {
        return Collections.EMPTY_LIST;
    }
}
