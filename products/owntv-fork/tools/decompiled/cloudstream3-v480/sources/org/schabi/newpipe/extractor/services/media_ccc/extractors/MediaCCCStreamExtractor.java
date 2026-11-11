package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.services.media_ccc.linkHandler.MediaCCCConferenceLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.media_ccc.linkHandler.MediaCCCStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.Stream;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.stream.VideoStream;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.LocaleCompat;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCStreamExtractor extends StreamExtractor {
    private JsonObject conferenceData;
    private JsonObject data;

    public MediaCCCStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public String getTextualUploadDate() {
        return this.data.getString("release_date");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return DateWrapper.fromOffsetDateTime(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() {
        return MediaCCCParsingHelper.getThumbnailsFromStreamItem(this.data);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() {
        return new Description(this.data.getString("description"), 3);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLength() {
        return this.data.getInt("length");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getViewCount() {
        return this.data.getInt("view_count");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() {
        return MediaCCCConferenceLinkHandlerFactory.CONFERENCE_PATH + getUploaderName();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() {
        return this.data.getString("conference_url").replaceFirst("https://(api\\.)?media\\.ccc\\.de/public/conferences/", "");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return MediaCCCParsingHelper.getImageListFromLogoImageUrl(this.conferenceData.getString("logo_url"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() throws ExtractionException {
        MediaFormat mediaFormat;
        JsonArray array = this.data.getArray("recordings");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.getObject(i);
            String string = object.getString("mime_type");
            if (string.startsWith("audio")) {
                if (string.endsWith("opus")) {
                    mediaFormat = MediaFormat.OPUS;
                } else if (string.endsWith("mpeg")) {
                    mediaFormat = MediaFormat.MP3;
                } else {
                    mediaFormat = string.endsWith("ogg") ? MediaFormat.OGG : null;
                }
                AudioStream.Builder averageBitrate = new AudioStream.Builder().setId(object.getString(ContentDisposition.Parameters.FileName, Stream.ID_UNKNOWN)).setContent(object.getString("recording_url"), true).setMediaFormat(mediaFormat).setAverageBitrate(-1);
                final String string2 = object.getString("language");
                if (string2 != null && !string2.contains("-")) {
                    averageBitrate.setAudioLocale(LocaleCompat.forLanguageTag(string2).orElseThrow(new Supplier() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCStreamExtractor$$ExternalSyntheticLambda0
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return MediaCCCStreamExtractor.lambda$getAudioStreams$0(string2);
                        }
                    }));
                }
                arrayList.add(averageBitrate.build());
            }
        }
        return arrayList;
    }

    static /* synthetic */ ParsingException lambda$getAudioStreams$0(String str) {
        return new ParsingException("Cannot convert this language to a locale: " + str);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoStreams() throws ExtractionException {
        MediaFormat mediaFormat;
        JsonArray array = this.data.getArray("recordings");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.getObject(i);
            String string = object.getString("mime_type");
            if (string.startsWith("video")) {
                if (string.endsWith("webm")) {
                    mediaFormat = MediaFormat.WEBM;
                } else {
                    mediaFormat = string.endsWith("mp4") ? MediaFormat.MPEG_4 : null;
                }
                arrayList.add(new VideoStream.Builder().setId(object.getString(ContentDisposition.Parameters.FileName, Stream.ID_UNKNOWN)).setContent(object.getString("recording_url"), true).setIsVideoOnly(false).setMediaFormat(mediaFormat).setResolution(object.getInt("height") + TtmlNode.TAG_P).build());
            }
        }
        return arrayList;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoOnlyStreams() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public StreamType getStreamType() {
        return StreamType.VIDEO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        String str = MediaCCCStreamLinkHandlerFactory.VIDEO_API_ENDPOINT + getId();
        try {
            this.data = JsonParser.object().from(downloader.get(str).responseBody());
            this.conferenceData = JsonParser.object().from(downloader.get(this.data.getString("conference_url")).responseBody());
        } catch (JsonParserException e) {
            throw new ExtractionException("Could not parse json returned by URL: " + str, e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.data.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getOriginalUrl() {
        return this.data.getString("frontend_link");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public Locale getLanguageInfo() throws ParsingException {
        return Localization.getLocaleFromThreeLetterCode(this.data.getString("original_language"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<String> getTags() {
        return JsonUtils.getStringListFromJsonArray(this.data.getArray("tags"));
    }
}
