package org.schabi.newpipe.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonArray$$ExternalSyntheticLambda0;
import com.grack.nanojson.JsonObject;
import j$.util.Collection;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandler;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.DeliveryMethod;
import org.schabi.newpipe.extractor.stream.Description;
import org.schabi.newpipe.extractor.stream.Stream;
import org.schabi.newpipe.extractor.stream.StreamExtractor;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.stream.VideoStream;

/* JADX INFO: loaded from: classes8.dex */
public class MediaCCCLiveStreamExtractor extends StreamExtractor {
    private static final String STREAMS = "streams";
    private static final String URL = "url";
    private static final String URLS = "urls";
    private JsonObject conference;
    private String group;
    private JsonObject room;

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public long getViewCount() {
        return -1L;
    }

    public MediaCCCLiveStreamExtractor(StreamingService streamingService, LinkHandler linkHandler) {
        super(streamingService, linkHandler);
        this.conference = null;
        this.group = "";
        this.room = null;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        JsonArray liveStreams = MediaCCCParsingHelper.getLiveStreams(downloader, getExtractorLocalization());
        for (int i = 0; i < liveStreams.size(); i++) {
            JsonObject object = liveStreams.getObject(i);
            JsonArray array = object.getArray("groups");
            for (int i2 = 0; i2 < array.size(); i2++) {
                String string = array.getObject(i2).getString("group");
                JsonArray array2 = array.getObject(i2).getArray("rooms");
                for (int i3 = 0; i3 < array2.size(); i3++) {
                    JsonObject object2 = array2.getObject(i3);
                    if (getId().equals(object.getString("slug") + "/" + object2.getString("slug"))) {
                        this.conference = object;
                        this.group = string;
                        this.room = object2;
                        return;
                    }
                }
            }
        }
        throw new ExtractionException("Could not find room matching id: '" + getId() + "'");
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public String getName() throws ParsingException {
        return this.room.getString("display");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public List<Image> getThumbnails() throws ParsingException {
        return MediaCCCParsingHelper.getThumbnailsFromLiveStreamItem(this.room);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public Description getDescription() throws ParsingException {
        return new Description(this.conference.getString("description") + " - " + this.group, 3);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderUrl() throws ParsingException {
        return "https://streaming.media.ccc.de/" + this.conference.getString("slug");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getUploaderName() throws ParsingException {
        return this.conference.getString("conference");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getDashMpdUrl() throws ParsingException {
        return getManifestOfDeliveryMethodWanted("dash");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getHlsUrl() {
        return getManifestOfDeliveryMethodWanted("hls");
    }

    @Nonnull
    private String getManifestOfDeliveryMethodWanted(@Nonnull final String str) {
        return (String) this.room.getArray(STREAMS).streamAsJsonObjects().map(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject(MediaCCCLiveStreamExtractor.URLS);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda3
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
                return ((JsonObject) obj).has(str);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JsonObject) obj).getObject(str).getString("url", "");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).findFirst().orElse("");
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<AudioStream> getAudioStreams() throws IOException, ExtractionException {
        return getStreams("audio", new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaCCCLiveStreamExtractor.lambda$getAudioStreams$3((MediaCCCLiveStreamExtractor.MediaCCCLiveStreamMapperDTO) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
    }

    static /* synthetic */ AudioStream lambda$getAudioStreams$3(MediaCCCLiveStreamMapperDTO mediaCCCLiveStreamMapperDTO) {
        AudioStream.Builder averageBitrate = new AudioStream.Builder().setId(mediaCCCLiveStreamMapperDTO.urlValue.getString("tech", Stream.ID_UNKNOWN)).setContent(mediaCCCLiveStreamMapperDTO.urlValue.getString("url"), true).setAverageBitrate(-1);
        if ("hls".equals(mediaCCCLiveStreamMapperDTO.urlKey)) {
            return averageBitrate.setDeliveryMethod(DeliveryMethod.HLS).build();
        }
        return averageBitrate.setMediaFormat(MediaFormat.getFromSuffix(mediaCCCLiveStreamMapperDTO.urlKey)).build();
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoStreams() throws IOException, ExtractionException {
        return getStreams("video", new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaCCCLiveStreamExtractor.lambda$getVideoStreams$4((MediaCCCLiveStreamExtractor.MediaCCCLiveStreamMapperDTO) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
    }

    static /* synthetic */ VideoStream lambda$getVideoStreams$4(MediaCCCLiveStreamMapperDTO mediaCCCLiveStreamMapperDTO) {
        JsonArray array = mediaCCCLiveStreamMapperDTO.streamJsonObj.getArray("videoSize");
        VideoStream.Builder resolution = new VideoStream.Builder().setId(mediaCCCLiveStreamMapperDTO.urlValue.getString("tech", Stream.ID_UNKNOWN)).setContent(mediaCCCLiveStreamMapperDTO.urlValue.getString("url"), true).setIsVideoOnly(false).setResolution(array.getInt(0) + "x" + array.getInt(1));
        if ("hls".equals(mediaCCCLiveStreamMapperDTO.urlKey)) {
            return resolution.setDeliveryMethod(DeliveryMethod.HLS).build();
        }
        return resolution.setMediaFormat(MediaFormat.getFromSuffix(mediaCCCLiveStreamMapperDTO.urlKey)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class MediaCCCLiveStreamMapperDTO {
        final JsonObject streamJsonObj;
        final String urlKey;
        final JsonObject urlValue;

        MediaCCCLiveStreamMapperDTO(JsonObject jsonObject, String str, JsonObject jsonObject2) {
            this.streamJsonObj = jsonObject;
            this.urlKey = str;
            this.urlValue = jsonObject2;
        }
    }

    private <T extends Stream> List<T> getStreams(@Nonnull final String str, @Nonnull Function<MediaCCCLiveStreamMapperDTO, T> function) {
        final Class<JsonObject> cls = JsonObject.class;
        return (List) Collection.EL.stream(this.room.getArray(STREAMS)).filter(new JsonArray$$ExternalSyntheticLambda0(JsonObject.class)).map(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function2) {
                return Function$CC.$default$andThen(this, function2);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (JsonObject) cls.cast(obj);
            }

            public /* synthetic */ Function compose(Function function2) {
                return Function$CC.$default$compose(this, function2);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda9
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
                return str.equals(((JsonObject) obj).getString("type"));
            }
        }).flatMap(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function2) {
                return Function$CC.$default$andThen(this, function2);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                JsonObject jsonObject = (JsonObject) obj;
                return Collection.EL.stream(jsonObject.getObject(MediaCCCLiveStreamExtractor.URLS).entrySet()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda6
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
                    public final boolean test(Object obj2) {
                        return MediaCCCLiveStreamExtractor.lambda$getStreams$6((Map.Entry) obj2);
                    }
                }).map(new Function() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda7
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function2) {
                        return Function$CC.$default$andThen(this, function2);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        return MediaCCCLiveStreamExtractor.lambda$getStreams$7(jsonObject, (Map.Entry) obj2);
                    }

                    public /* synthetic */ Function compose(Function function2) {
                        return Function$CC.$default$compose(this, function2);
                    }
                });
            }

            public /* synthetic */ Function compose(Function function2) {
                return Function$CC.$default$compose(this, function2);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCLiveStreamExtractor$$ExternalSyntheticLambda1
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
                return MediaCCCLiveStreamExtractor.lambda$getStreams$9((MediaCCCLiveStreamExtractor.MediaCCCLiveStreamMapperDTO) obj);
            }
        }).map(function).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getStreams$6(Map.Entry entry) {
        return entry.getValue() instanceof JsonObject;
    }

    static /* synthetic */ MediaCCCLiveStreamMapperDTO lambda$getStreams$7(JsonObject jsonObject, Map.Entry entry) {
        return new MediaCCCLiveStreamMapperDTO(jsonObject, (String) entry.getKey(), (JsonObject) entry.getValue());
    }

    static /* synthetic */ boolean lambda$getStreams$9(MediaCCCLiveStreamMapperDTO mediaCCCLiveStreamMapperDTO) {
        return !"dash".equals(mediaCCCLiveStreamMapperDTO.urlKey);
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public List<VideoStream> getVideoOnlyStreams() {
        return Collections.EMPTY_LIST;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    public StreamType getStreamType() throws ParsingException {
        return StreamType.LIVE_STREAM;
    }

    @Override // org.schabi.newpipe.extractor.stream.StreamExtractor
    @Nonnull
    public String getCategory() {
        return this.group;
    }
}
