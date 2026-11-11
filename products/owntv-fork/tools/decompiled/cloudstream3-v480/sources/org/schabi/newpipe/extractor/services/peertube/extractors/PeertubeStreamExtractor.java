package org.schabi.newpipe.extractor.services.peertube.extractors;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import j$.util.stream.Stream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.channel.tabs.ChannelTabs;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.downloader.Response;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.exceptions.ReCaptchaException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeSearchQueryHandlerFactory;
import org.schabi.newpipe.extractor.services.peertube.linkHandler.PeertubeStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.DeliveryMethod;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.Frameset;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.StreamSegment;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.stream.SubtitlesStream;
import org.schabi.newpipe.extractor.stream.VideoStream;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public class PeertubeStreamExtractor extends StreamExtractor {
    private static final String ACCOUNT_HOST = "account.host";
    private static final String ACCOUNT_NAME = "account.name";
    private static final String FILES = "files";
    private static final String FILE_DOWNLOAD_URL = "fileDownloadUrl";
    private static final String FILE_URL = "fileUrl";
    private static final String PLAYLIST_URL = "playlistUrl";
    private static final String RESOLUTION_ID = "resolution.id";
    private static final String STREAMING_PLAYLISTS = "streamingPlaylists";
    private final List<AudioStream> audioStreams;
    private final String baseUrl;
    private JsonObject json;
    private final List<SubtitlesStream> subtitles;
    private ParsingException subtitlesException;
    private final List<VideoStream> videoStreams;

    public PeertubeStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) throws ParsingException {
        super(streamingService, linkHandler);
        this.subtitles = new ArrayList();
        this.audioStreams = new ArrayList();
        this.videoStreams = new ArrayList();
        this.subtitlesException = null;
        this.baseUrl = getBaseUrl();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public String getTextualUploadDate() throws ParsingException {
        return JsonUtils.getString(this.json, "publishedAt");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public DateWrapper getUploadDate() throws ParsingException {
        return DateWrapper.fromInstant(getTextualUploadDate());
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return PeertubeParsingHelper.getThumbnailsFromPlaylistOrVideoItem(this.baseUrl, this.json);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        try {
            String string = JsonUtils.getString(this.json, "description");
            if (string.length() == 250 && string.substring(247).equals("...")) {
                Downloader downloader = NewPipe.getDownloader();
                try {
                    string = JsonUtils.getString(JsonParser.object().from(downloader.get(this.baseUrl + PeertubeStreamLinkHandlerFactory.VIDEO_API_ENDPOINT + getId() + "/description").responseBody()), "description");
                } catch (JsonParserException | IOException | ReCaptchaException unused) {
                }
            }
            return new Description(string, 2);
        } catch (ParsingException unused2) {
            return Description.EMPTY_DESCRIPTION;
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public int getAgeLimit() throws ParsingException {
        return JsonUtils.getBoolean(this.json, "nsfw").booleanValue() ? 18 : 0;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLength() {
        return this.json.getLong(TypedValues.TransitionType.S_DURATION);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getTimeStamp() throws ParsingException {
        long timestampSeconds = getTimestampSeconds("((#|&|\\?)start=\\d{0,3}h?\\d{0,3}m?\\d{1,3}s?)");
        if (timestampSeconds == -2) {
            return 0L;
        }
        return timestampSeconds;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getViewCount() {
        return this.json.getLong("views");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getLikeCount() {
        return this.json.getLong(ChannelTabs.LIKES);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getDislikeCount() {
        return this.json.getLong("dislikes");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() throws ParsingException {
        String string = JsonUtils.getString(this.json, ACCOUNT_NAME);
        String string2 = JsonUtils.getString(this.json, ACCOUNT_HOST);
        return getService().getChannelLHFactory().fromId("accounts/" + string + "@" + string2, this.baseUrl).getUrl();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() throws ParsingException {
        return JsonUtils.getString(this.json, "account.displayName");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getUploaderAvatars() {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(this.baseUrl, this.json.getObject("account"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getSubChannelUrl() throws ParsingException {
        return JsonUtils.getString(this.json, "channel.url");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getSubChannelName() throws ParsingException {
        return JsonUtils.getString(this.json, "channel.displayName");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getSubChannelAvatars() {
        return PeertubeParsingHelper.getAvatarsFromOwnerAccountOrVideoChannelObject(this.baseUrl, this.json.getObject(TvContractCompat.PARAM_CHANNEL));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getHlsUrl() {
        assertPageFetched();
        if (getStreamType() == StreamType.VIDEO_STREAM && !Utils.isNullOrEmpty(this.json.getObject(FILES))) {
            return this.json.getObject(FILES).getString(PLAYLIST_URL, "");
        }
        return this.json.getArray(STREAMING_PLAYLISTS).getObject(0).getString(PLAYLIST_URL, "");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() throws ParsingException {
        assertPageFetched();
        if (this.audioStreams.isEmpty() && this.videoStreams.isEmpty() && getStreamType() == StreamType.VIDEO_STREAM) {
            getStreams();
        }
        return this.audioStreams;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoStreams() throws ExtractionException {
        assertPageFetched();
        if (this.videoStreams.isEmpty()) {
            if (getStreamType() == StreamType.VIDEO_STREAM) {
                getStreams();
            } else {
                extractLiveVideoStreams();
            }
        }
        return this.videoStreams;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoOnlyStreams() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<SubtitlesStream> getSubtitlesDefault() throws ParsingException {
        ParsingException parsingException = this.subtitlesException;
        if (parsingException != null) {
            throw parsingException;
        }
        return this.subtitles;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<SubtitlesStream> getSubtitles(final MediaFormat mediaFormat) throws ParsingException {
        ParsingException parsingException = this.subtitlesException;
        if (parsingException != null) {
            throw parsingException;
        }
        return (List) Collection.EL.stream(this.subtitles).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeStreamExtractor$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PeertubeStreamExtractor.lambda$getSubtitles$0(mediaFormat, (SubtitlesStream) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getSubtitles$0(MediaFormat mediaFormat, SubtitlesStream subtitlesStream) {
        return subtitlesStream.getFormat() == mediaFormat;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public StreamType getStreamType() {
        return this.json.getBoolean("isLive") ? StreamType.LIVE_STREAM : StreamType.VIDEO_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nullable
    public StreamInfoItemsCollector getRelatedItems() throws IOException, ExtractionException {
        String relatedItemsUrl;
        List<String> tags = getTags();
        if (tags.isEmpty()) {
            relatedItemsUrl = this.baseUrl + "/api/v1/accounts/" + JsonUtils.getString(this.json, ACCOUNT_NAME) + "@" + JsonUtils.getString(this.json, ACCOUNT_HOST) + "/videos?start=0&count=8";
        } else {
            relatedItemsUrl = getRelatedItemsUrl(tags);
        }
        if (Utils.isBlank(relatedItemsUrl)) {
            return null;
        }
        StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        getStreamsFromApi(streamInfoItemsCollector, relatedItemsUrl);
        return streamInfoItemsCollector;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<String> getTags() {
        return JsonUtils.getStringListFromJsonArray(this.json.getArray("tags"));
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getSupportInfo() {
        try {
            return JsonUtils.getString(this.json, "support");
        } catch (ParsingException unused) {
            return "";
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<StreamSegment> getStreamSegments() throws ParsingException {
        ArrayList arrayList = new ArrayList();
        try {
            JsonObject jsonObjectFetchSubApiContent = fetchSubApiContent("chapters");
            if (jsonObjectFetchSubApiContent != null && jsonObjectFetchSubApiContent.has("chapters")) {
                JsonArray array = jsonObjectFetchSubApiContent.getArray("chapters");
                for (int i = 0; i < array.size(); i++) {
                    JsonObject object = array.getObject(i);
                    arrayList.add(new StreamSegment(object.getString("title"), object.getInt("timecode")));
                }
            }
            return arrayList;
        } catch (IOException | ReCaptchaException e) {
            throw new ParsingException("Could not get stream segments", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Frameset> getFrames() throws ExtractionException {
        ArrayList arrayList = new ArrayList();
        try {
            JsonObject jsonObjectFetchSubApiContent = fetchSubApiContent("storyboards");
            if (jsonObjectFetchSubApiContent != null && jsonObjectFetchSubApiContent.has("storyboards")) {
                for (Object obj : jsonObjectFetchSubApiContent.getArray("storyboards")) {
                    if (obj instanceof JsonObject) {
                        JsonObject jsonObject = (JsonObject) obj;
                        String string = jsonObject.getString("storyboardPath");
                        int i = jsonObject.getInt("spriteWidth");
                        int i2 = jsonObject.getInt("spriteHeight");
                        int i3 = jsonObject.getInt("totalWidth") / i;
                        int i4 = jsonObject.getInt("totalHeight") / i2;
                        arrayList.add(new Frameset(UByte$$ExternalSyntheticBackport0.m(new Object[]{this.baseUrl + string}), i, i2, i3 * i4, jsonObject.getInt("spriteDuration") * 1000, i3, i4));
                    }
                }
            }
            return arrayList;
        } catch (IOException | ReCaptchaException e) {
            throw new ExtractionException("Could not get frames", e);
        }
    }

    @Nonnull
    private String getRelatedItemsUrl(@Nonnull List<String> list) {
        String str = this.baseUrl + PeertubeSearchQueryHandlerFactory.SEARCH_ENDPOINT_VIDEOS;
        StringBuilder sb = new StringBuilder("start=0&count=8&sort=-createdAt");
        for (String str2 : list) {
            sb.append("&tagsOneOf=");
            sb.append(Utils.encodeUrlUtf8(str2));
        }
        return str + "?" + ((Object) sb);
    }

    private void getStreamsFromApi(StreamInfoItemsCollector streamInfoItemsCollector, String str) throws ReCaptchaException, IOException, ParsingException {
        JsonObject jsonObjectFrom;
        Response response = getDownloader().get(str);
        if (response == null || Utils.isBlank(response.responseBody())) {
            jsonObjectFrom = null;
        } else {
            try {
                jsonObjectFrom = JsonParser.object().from(response.responseBody());
            } catch (JsonParserException e) {
                throw new ParsingException("Could not parse json data for related videos", e);
            }
        }
        if (jsonObjectFrom != null) {
            collectStreamsFrom(streamInfoItemsCollector, jsonObjectFrom);
        }
    }

    private void collectStreamsFrom(StreamInfoItemsCollector streamInfoItemsCollector, JsonObject jsonObject) throws ParsingException {
        try {
            for (Object obj : (JsonArray) JsonUtils.getValue(jsonObject, "data")) {
                if (obj instanceof JsonObject) {
                    PeertubeStreamInfoItemExtractor peertubeStreamInfoItemExtractor = new PeertubeStreamInfoItemExtractor((JsonObject) obj, this.baseUrl);
                    if (!peertubeStreamInfoItemExtractor.getUrl().equals(getUrl())) {
                        streamInfoItemsCollector.commit((StreamInfoItemExtractor) peertubeStreamInfoItemExtractor);
                    }
                }
            }
        } catch (Exception e) {
            throw new ParsingException("Could not extract related videos", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        Response response = downloader.get(this.baseUrl + PeertubeStreamLinkHandlerFactory.VIDEO_API_ENDPOINT + getId());
        if (response != null) {
            setInitialData(response.responseBody());
            loadSubtitles();
            return;
        }
        throw new ExtractionException("Could not extract PeerTube channel data");
    }

    private void setInitialData(String str) throws ExtractionException {
        try {
            JsonObject jsonObjectFrom = JsonParser.object().from(str);
            this.json = jsonObjectFrom;
            if (jsonObjectFrom == null) {
                throw new ExtractionException("Could not extract PeerTube stream data");
            }
            PeertubeParsingHelper.validate(jsonObjectFrom);
        } catch (JsonParserException e) {
            throw new ExtractionException("Could not extract PeerTube stream data", e);
        }
    }

    private void loadSubtitles() {
        if (this.subtitles.isEmpty()) {
            try {
                for (Object obj : JsonUtils.getArray(JsonParser.object().from(getDownloader().get(this.baseUrl + PeertubeStreamLinkHandlerFactory.VIDEO_API_ENDPOINT + getId() + "/captions").responseBody()), "data")) {
                    if (obj instanceof JsonObject) {
                        JsonObject jsonObject = (JsonObject) obj;
                        String str = this.baseUrl + JsonUtils.getString(jsonObject, "captionPath");
                        String string = JsonUtils.getString(jsonObject, "language.id");
                        MediaFormat fromSuffix = MediaFormat.getFromSuffix(str.substring(str.lastIndexOf(".") + 1));
                        if (fromSuffix != null && !Utils.isNullOrEmpty(string)) {
                            this.subtitles.add(new SubtitlesStream.Builder().setContent(str, true).setMediaFormat(fromSuffix).setLanguageCode(string).setAutoGenerated(false).build());
                        }
                    }
                }
            } catch (Exception e) {
                this.subtitlesException = new ParsingException("Could not get subtitles", e);
            }
        }
    }

    private void extractLiveVideoStreams() throws ParsingException {
        try {
            Stream<R> map = this.json.getArray(STREAMING_PLAYLISTS).streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeStreamExtractor$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    JsonObject jsonObject = (JsonObject) obj;
                    return new VideoStream.Builder().setId(String.valueOf(jsonObject.getInt("id", -1))).setContent(jsonObject.getString(PeertubeStreamExtractor.PLAYLIST_URL, ""), true).setIsVideoOnly(false).setResolution("").setMediaFormat(MediaFormat.MPEG_4).setDeliveryMethod(DeliveryMethod.HLS).build();
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            });
            final List<VideoStream> list = this.videoStreams;
            Objects.requireNonNull(list);
            map.forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.peertube.extractors.PeertubeStreamExtractor$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    list.add((VideoStream) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        } catch (Exception e) {
            throw new ParsingException("Could not get video streams", e);
        }
    }

    private void getStreams() throws ParsingException {
        getStreamsFromArray(this.json.getArray(FILES), "");
        try {
            for (JsonObject jsonObject : this.json.getArray(STREAMING_PLAYLISTS).streamAsJsonObjects()) {
                getStreamsFromArray(jsonObject.getArray(FILES), jsonObject.getString(PLAYLIST_URL));
            }
        } catch (Exception e) {
            throw new ParsingException("Could not get streams", e);
        }
    }

    private void getStreamsFromArray(@Nonnull JsonArray jsonArray, String str) throws ParsingException {
        PeertubeStreamExtractor peertubeStreamExtractor;
        String str2;
        try {
            boolean z = !Utils.isNullOrEmpty(str) && str.endsWith("-master.m3u8");
            for (JsonObject jsonObject : jsonArray.streamAsJsonObjects()) {
                String string = JsonUtils.getString(jsonObject, jsonObject.has(FILE_URL) ? FILE_URL : FILE_DOWNLOAD_URL);
                if (Utils.isNullOrEmpty(string)) {
                    return;
                }
                String string2 = JsonUtils.getString(jsonObject, "resolution.label");
                String str3 = jsonObject.has(FILE_URL) ? FILE_URL : FILE_DOWNLOAD_URL;
                if (string2.toLowerCase().contains("audio")) {
                    peertubeStreamExtractor = this;
                    str2 = str;
                    peertubeStreamExtractor.addNewAudioStream(jsonObject, z, string2, str3, string, str2);
                } else {
                    peertubeStreamExtractor = this;
                    str2 = str;
                    peertubeStreamExtractor.addNewVideoStream(jsonObject, z, string2, str3, string, str2);
                }
                this = peertubeStreamExtractor;
                str = str2;
            }
        } catch (Exception e) {
            throw new ParsingException("Could not get streams from array", e);
        }
    }

    @Nonnull
    private String getHlsPlaylistUrlFromFragmentedFileUrl(@Nonnull JsonObject jsonObject, @Nonnull String str, @Nonnull String str2, @Nonnull String str3) throws ParsingException {
        if (FILE_DOWNLOAD_URL.equals(str)) {
            str3 = JsonUtils.getString(jsonObject, FILE_URL);
        }
        return str3.replace("-fragmented." + str2, ".m3u8");
    }

    @Nonnull
    private String getHlsPlaylistUrlFromMasterPlaylist(@Nonnull JsonObject jsonObject, @Nonnull String str) throws ParsingException {
        return str.replace("master", JsonUtils.getNumber(jsonObject, RESOLUTION_ID).toString());
    }

    private void addNewAudioStream(@Nonnull JsonObject jsonObject, boolean z, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable String str4) throws ParsingException {
        String hlsPlaylistUrlFromMasterPlaylist;
        String strSubstring = str3.substring(str3.lastIndexOf(".") + 1);
        MediaFormat fromSuffix = MediaFormat.getFromSuffix(strSubstring);
        String str5 = str + "-" + strSubstring;
        this.audioStreams.add(new AudioStream.Builder().setId(str5 + "-" + str2 + "-" + DeliveryMethod.PROGRESSIVE_HTTP).setContent(str3, true).setMediaFormat(fromSuffix).setAverageBitrate(-1).build());
        if (!Utils.isNullOrEmpty(str4)) {
            if (z) {
                hlsPlaylistUrlFromMasterPlaylist = getHlsPlaylistUrlFromFragmentedFileUrl(jsonObject, str2, strSubstring, str3);
            } else {
                hlsPlaylistUrlFromMasterPlaylist = getHlsPlaylistUrlFromMasterPlaylist(jsonObject, str4);
            }
            AudioStream audioStreamBuild = new AudioStream.Builder().setId(str5 + "-" + DeliveryMethod.HLS).setContent(hlsPlaylistUrlFromMasterPlaylist, true).setDeliveryMethod(DeliveryMethod.HLS).setMediaFormat(fromSuffix).setAverageBitrate(-1).setManifestUrl(str4).build();
            if (!org.schabi.newpipe.extractor.stream.Stream.containSimilarStream(audioStreamBuild, this.audioStreams)) {
                this.audioStreams.add(audioStreamBuild);
            }
        }
        String string = JsonUtils.getString(jsonObject, "torrentUrl");
        if (Utils.isNullOrEmpty(string)) {
            return;
        }
        this.audioStreams.add(new AudioStream.Builder().setId(str5 + "-" + str2 + "-" + DeliveryMethod.TORRENT).setContent(string, true).setDeliveryMethod(DeliveryMethod.TORRENT).setMediaFormat(fromSuffix).setAverageBitrate(-1).build());
    }

    private void addNewVideoStream(@Nonnull JsonObject jsonObject, boolean z, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable String str4) throws ParsingException {
        String hlsPlaylistUrlFromMasterPlaylist;
        String strSubstring = str3.substring(str3.lastIndexOf(".") + 1);
        MediaFormat fromSuffix = MediaFormat.getFromSuffix(strSubstring);
        String str5 = str + "-" + strSubstring;
        boolean z2 = jsonObject.has("hasAudio") && !jsonObject.getBoolean("hasAudio");
        this.videoStreams.add(new VideoStream.Builder().setId(str5 + "-" + str2 + "-" + DeliveryMethod.PROGRESSIVE_HTTP).setContent(str3, true).setIsVideoOnly(z2).setResolution(str).setMediaFormat(fromSuffix).build());
        if (!Utils.isNullOrEmpty(str4)) {
            if (z) {
                hlsPlaylistUrlFromMasterPlaylist = getHlsPlaylistUrlFromFragmentedFileUrl(jsonObject, str2, strSubstring, str3);
            } else {
                hlsPlaylistUrlFromMasterPlaylist = getHlsPlaylistUrlFromMasterPlaylist(jsonObject, str4);
            }
            VideoStream videoStreamBuild = new VideoStream.Builder().setId(str5 + "-" + DeliveryMethod.HLS).setContent(hlsPlaylistUrlFromMasterPlaylist, true).setIsVideoOnly(z2).setDeliveryMethod(DeliveryMethod.HLS).setResolution(str).setMediaFormat(fromSuffix).setManifestUrl(str4).build();
            if (!org.schabi.newpipe.extractor.stream.Stream.containSimilarStream(videoStreamBuild, this.videoStreams)) {
                this.videoStreams.add(videoStreamBuild);
            }
        }
        String string = JsonUtils.getString(jsonObject, "torrentUrl");
        if (Utils.isNullOrEmpty(string)) {
            return;
        }
        this.videoStreams.add(new VideoStream.Builder().setId(str5 + "-" + str2 + "-" + DeliveryMethod.TORRENT).setContent(string, true).setIsVideoOnly(z2).setDeliveryMethod(DeliveryMethod.TORRENT).setResolution(str).setMediaFormat(fromSuffix).build());
    }

    @Nullable
    private JsonObject fetchSubApiContent(@Nonnull String str) throws ReCaptchaException, IOException, ParsingException {
        Response response = getDownloader().get(this.baseUrl + PeertubeStreamLinkHandlerFactory.VIDEO_API_ENDPOINT + getId() + "/" + str);
        if (response == null) {
            throw new ParsingException("Could not get segments from API.");
        }
        if (response.responseCode() == 400) {
            return null;
        }
        if (response.responseCode() != 200) {
            throw new ParsingException("Could not get segments from API. Response code: " + response.responseCode());
        }
        try {
            return JsonParser.object().from(response.responseBody());
        } catch (JsonParserException e) {
            throw new ParsingException("Could not parse json data for segments", e);
        }
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return JsonUtils.getString(this.json, "name");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getHost() throws ParsingException {
        return JsonUtils.getString(this.json, ACCOUNT_HOST);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public StreamExtractor.Privacy getPrivacy() {
        int i = this.json.getObject("privacy").getInt("id");
        if (i == 1) {
            return StreamExtractor.Privacy.PUBLIC;
        }
        if (i == 2) {
            return StreamExtractor.Privacy.UNLISTED;
        }
        if (i == 3) {
            return StreamExtractor.Privacy.PRIVATE;
        }
        if (i == 4) {
            return StreamExtractor.Privacy.INTERNAL;
        }
        return StreamExtractor.Privacy.OTHER;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getCategory() throws ParsingException {
        return JsonUtils.getString(this.json, "category.label");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getLicence() throws ParsingException {
        return JsonUtils.getString(this.json, "licence.label");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public Locale getLanguageInfo() {
        try {
            return new Locale(JsonUtils.getString(this.json, "language.id"));
        } catch (ParsingException unused) {
            return null;
        }
    }
}
