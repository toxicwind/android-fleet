package org.schabi.newpipe.extractor.services.soundcloud.extractors;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.common.internal.ImagesContract;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import io.ktor.http.LinkHeader;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ContentNotAvailableException;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.GeographicRestrictionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.SoundCloudGoPlusContentException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.soundcloud.SoundcloudParsingHelper;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.DeliveryMethod;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.Stream;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.stream.VideoStream;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class SoundcloudStreamExtractor extends StreamExtractor {
    private boolean isAvailable;
    private JsonObject track;

    public SoundcloudStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
        this.isAvailable = true;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        JsonObject jsonObjectResolveFor = SoundcloudParsingHelper.resolveFor(downloader, getUrl());
        this.track = jsonObjectResolveFor;
        String string = jsonObjectResolveFor.getString("policy", "");
        if (string.equals("ALLOW") || string.equals("MONETIZE")) {
            return;
        }
        this.isAvailable = false;
        if (string.equals("SNIP")) {
            throw new SoundCloudGoPlusContentException();
        }
        if (string.equals("BLOCK")) {
            throw new GeographicRestrictionException("This track is not available in user's country");
        }
        throw new ContentNotAvailableException("Content not available: policy " + string);
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getId() {
        return String.valueOf(this.track.getLong("id"));
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() {
        return this.track.getString("title");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public String getTextualUploadDate() {
        return this.track.getString("created_at");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public DateWrapper getUploadDate() throws ParsingException {
        return SoundcloudParsingHelper.parseDate(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return SoundcloudParsingHelper.getAllImagesFromTrackObject(this.track);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() {
        return new Description(this.track.getString("description"), 3);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLength() {
        return this.track.getLong(TypedValues.TransitionType.S_DURATION) / 1000;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getTimeStamp() throws ParsingException {
        long timestampSeconds = getTimestampSeconds("(#t=\\d{0,3}h?\\d{0,3}m?\\d{1,3}s?)");
        if (timestampSeconds == -2) {
            return 0L;
        }
        return timestampSeconds;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getViewCount() {
        return this.track.getLong("playback_count");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLikeCount() {
        return this.track.getLong("likes_count", -1L);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() {
        return SoundcloudParsingHelper.getUploaderUrl(this.track);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() {
        return SoundcloudParsingHelper.getUploaderName(this.track);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public boolean isUploaderVerified() throws ParsingException {
        return this.track.getObject("user").getBoolean("verified");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return SoundcloudParsingHelper.getAllImagesFromArtworkOrAvatarUrl(SoundcloudParsingHelper.getAvatarUrl(this.track));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() throws ExtractionException {
        ArrayList arrayList = new ArrayList();
        if (this.track.getBoolean("streamable") && this.isAvailable) {
            try {
                JsonArray array = this.track.getObject(LinkHeader.Parameters.Media).getArray("transcodings");
                if (!Utils.isNullOrEmpty(array)) {
                    extractAudioStreams(array, arrayList);
                    return arrayList;
                }
            } catch (NullPointerException e) {
                throw new ExtractionException("Could not get audio streams", e);
            }
        }
        return arrayList;
    }

    @Nonnull
    private String getTranscodingUrl(String str) throws IOException, ExtractionException {
        String str2 = str + "?client_id=" + SoundcloudParsingHelper.clientId();
        String string = this.track.getString("track_authorization");
        if (!Utils.isNullOrEmpty(string)) {
            str2 = str2 + "&track_authorization=" + string;
        }
        try {
            return JsonParser.object().from(NewPipe.getDownloader().get(str2).responseBody()).getString(ImagesContract.URL);
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse streamable URL", e);
        }
    }

    private void extractAudioStreams(@Nonnull JsonArray jsonArray, final List<AudioStream> list) {
        jsonArray.streamAsJsonObjects().forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.soundcloud.extractors.SoundcloudStreamExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m10966x25be573d(list, (JsonObject) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$extractAudioStreams$0$org-schabi-newpipe-extractor-services-soundcloud-extractors-SoundcloudStreamExtractor, reason: not valid java name */
    /* synthetic */ void m10966x25be573d(List list, JsonObject jsonObject) {
        String string = jsonObject.getString(ImagesContract.URL);
        if (Utils.isNullOrEmpty(string)) {
            return;
        }
        try {
            String string2 = jsonObject.getString("preset", Stream.ID_UNKNOWN);
            String string3 = jsonObject.getObject("format").getString("protocol");
            if (string3.contains("encrypted")) {
                return;
            }
            AudioStream.Builder id = new AudioStream.Builder().setId(string2);
            if (string3.equals("hls")) {
                id.setDeliveryMethod(DeliveryMethod.HLS);
            }
            id.setContent(getTranscodingUrl(string), true);
            if (string2.contains(HlsSegmentFormat.MP3)) {
                id.setMediaFormat(MediaFormat.MP3);
                id.setAverageBitrate(128);
            } else if (string2.contains("opus")) {
                id.setMediaFormat(MediaFormat.OPUS);
                id.setAverageBitrate(64);
            } else {
                if (!string2.contains("aac_160k")) {
                    return;
                }
                id.setMediaFormat(MediaFormat.M4A);
                id.setAverageBitrate(160);
            }
            AudioStream audioStreamBuild = id.build();
            if (Stream.containSimilarStream(audioStreamBuild, list)) {
                return;
            }
            list.add(audioStreamBuild);
        } catch (IOException | ExtractionException unused) {
        }
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
    @Nullable
    public StreamInfoItemsCollector getRelatedItems() throws IOException, ExtractionException {
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        SoundcloudParsingHelper.getStreamsFromApi(streamInfoItemsCollector, "https://api-v2.soundcloud.com/tracks/" + Utils.encodeUrlUtf8(getId()) + "/related?client_id=" + Utils.encodeUrlUtf8(SoundcloudParsingHelper.clientId()));
        return streamInfoItemsCollector;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public StreamExtractor.Privacy getPrivacy() {
        return this.track.getString("sharing").equals("public") ? StreamExtractor.Privacy.PUBLIC : StreamExtractor.Privacy.PRIVATE;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getCategory() {
        return this.track.getString("genre");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getLicence() {
        return this.track.getString("license");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<String> getTags() {
        String[] strArrSplit = this.track.getString("tag_list").split(Stream.ID_UNKNOWN);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (String str : strArrSplit) {
            if (str.startsWith("\"")) {
                sb.append(str.replace("\"", ""));
                z = true;
            } else if (z) {
                if (str.endsWith("\"")) {
                    sb.append(Stream.ID_UNKNOWN);
                    sb.append(str.replace("\"", ""));
                    arrayList.add(sb.toString());
                    z = false;
                } else {
                    sb.append(Stream.ID_UNKNOWN);
                    sb.append(str);
                }
            } else if (!str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
