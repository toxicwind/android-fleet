package org.schabi.newpipe.extractor.services.youtube.extractors.kiosk;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.google.android.gms.actions.SearchIntents;
import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonWriter;
import j$.time.LocalDate;
import j$.time.ZoneOffset;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import org.schabi.newpipe.extractor.Image;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.downloader.Downloader;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.kiosk.KioskExtractor;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.DateWrapper;
import org.schabi.newpipe.extractor.localization.Localization;
import org.schabi.newpipe.extractor.services.youtube.InnertubeClientRequestInfo;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.schabi.newpipe.extractor.services.youtube.linkHandler.YoutubeStreamLinkHandlerFactory;
import org.schabi.newpipe.extractor.stream.ContentAvailability;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor;
import org.schabi.newpipe.extractor.stream.StreamInfoItemsCollector;
import org.schabi.newpipe.extractor.stream.StreamType;
import org.schabi.newpipe.extractor.utils.JsonUtils;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
abstract class YoutubeChartsBaseKioskExtractor extends KioskExtractor<StreamInfoItem> {
    protected static final String YT_CHARTS_ENDPOINT = "https://charts.youtube.com/youtubei/v1/browse?alt=json&prettyPrint=false";
    protected static final Set<String> YT_CHARTS_SUPPORTED_COUNTRY_CODES = UByte$$ExternalSyntheticBackport0.m9013m((Object[]) new String[]{"AE", "AR", "AT", "AU", "BE", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CZ", "DE", "DK", "DO", "EC", "EE", "EG", "ES", "FI", "FR", "GB", "GT", "HN", "HU", "ID", "IE", "IL", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN, "IS", "IT", "JP", "KE", "KR", "LU", "MX", "NG", "NI", "NL", "NO", "NZ", "PA", "PE", "PL", "PT", "PY", "RO", "RS", "RU", "SA", "SE", "SV", "TR", "TZ", "UA", "UG", "US", "UY", "ZA", "ZW"});
    protected JsonObject browseResponse;
    protected final String chartType;

    @Override // org.schabi.newpipe.extractor.kiosk.KioskExtractor, org.schabi.newpipe.extractor.Extractor
    @Nonnull
    public abstract String getName() throws ParsingException;

    protected YoutubeChartsBaseKioskExtractor(StreamingService streamingService, ListLinkHandler listLinkHandler, String str, String str2) {
        super(streamingService, listLinkHandler, str);
        this.chartType = str2;
    }

    @Override // org.schabi.newpipe.extractor.Extractor
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        Localization extractorLocalization = getExtractorLocalization();
        ContentCountry extractorContentCountry = getExtractorContentCountry();
        InnertubeClientRequestInfo innertubeClientRequestInfoOfWebMusicAnalyticsChartsClient = InnertubeClientRequestInfo.ofWebMusicAnalyticsChartsClient();
        byte[] bytes = JsonWriter.string(YoutubeParsingHelper.prepareJsonBuilder(getExtractorLocalization(), extractorContentCountry, innertubeClientRequestInfoOfWebMusicAnalyticsChartsClient, null).value("browseId", "FEmusic_analytics_charts_home").value(SearchIntents.EXTRA_QUERY, "perspective=CHART_DETAILS&chart_params_country_code=" + extractorContentCountry.getCountryCode() + "&chart_params_chart_type=" + this.chartType).done()).getBytes(StandardCharsets.UTF_8);
        HashMap map = new HashMap(YoutubeParsingHelper.getOriginReferrerHeaders("https://charts.youtube.com"));
        map.putAll(YoutubeParsingHelper.getClientHeaders(innertubeClientRequestInfoOfWebMusicAnalyticsChartsClient.clientInfo.clientId, innertubeClientRequestInfoOfWebMusicAnalyticsChartsClient.clientInfo.clientVersion));
        this.browseResponse = JsonUtils.toJsonObject(YoutubeParsingHelper.getValidJsonResponseBody(getDownloader().postWithContentTypeJson(YT_CHARTS_ENDPOINT, map, bytes, extractorLocalization)));
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    @Nonnull
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        JsonArray array = this.browseResponse.getObject("contents").getObject("sectionListRenderer").getArray("contents").getObject(0).getObject("musicAnalyticsSectionRenderer").getObject("content").getArray("videos").getObject(0).getArray("videoViews");
        final StreamInfoItemsCollector streamInfoItemsCollector = new StreamInfoItemsCollector(getServiceId());
        array.streamAsJsonObjects().forEachOrdered(new Consumer() { // from class: org.schabi.newpipe.extractor.services.youtube.extractors.kiosk.YoutubeChartsBaseKioskExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                streamInfoItemsCollector.commit((StreamInfoItemExtractor) new YoutubeChartsBaseKioskExtractor.YoutubeChartsVideoInfoItemExtractor((JsonObject) obj));
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        return new ListExtractor.InfoItemsPage<>(streamInfoItemsCollector, null);
    }

    @Override // org.schabi.newpipe.extractor.ListExtractor
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(Page page) {
        return ListExtractor.InfoItemsPage.emptyPage();
    }

    static final class YoutubeChartsVideoInfoItemExtractor implements StreamInfoItemExtractor {

        @Nonnull
        private final JsonObject videoObject;

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public /* synthetic */ ContentAvailability getContentAvailability() {
            return ContentAvailability.UNKNOWN;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public /* synthetic */ String getShortDescription() {
            return StreamInfoItemExtractor.CC.$default$getShortDescription(this);
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        @Nullable
        public String getTextualUploadDate() {
            return null;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public /* synthetic */ List getUploaderAvatars() {
            return Collections.EMPTY_LIST;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public long getViewCount() {
            return -1L;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public boolean isAd() {
            return false;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public /* synthetic */ boolean isShortFormContent() {
            return StreamInfoItemExtractor.CC.$default$isShortFormContent(this);
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public boolean isUploaderVerified() {
            return false;
        }

        YoutubeChartsVideoInfoItemExtractor(@Nonnull JsonObject jsonObject) {
            this.videoObject = jsonObject;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public StreamType getStreamType() {
            return StreamType.VIDEO_STREAM;
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public long getDuration() {
            return this.videoObject.getInt("videoDuration", -1);
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public String getUploaderName() {
            return this.videoObject.getString("channelName");
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        public String getUploaderUrl() throws ParsingException {
            String string = this.videoObject.getString("externalChannelId");
            if (Utils.isNullOrEmpty(string)) {
                throw new ParsingException("Could not get channel ID");
            }
            return YoutubeChannelLinkHandlerFactory.getInstance().getUrl("channel/" + string);
        }

        @Override // org.schabi.newpipe.extractor.stream.StreamInfoItemExtractor
        @Nonnull
        public DateWrapper getUploadDate() {
            JsonObject object = this.videoObject.getObject("releaseDate");
            return new DateWrapper(LocalDate.of(object.getInt("year"), object.getInt("month"), object.getInt("day")).atStartOfDay(ZoneOffset.UTC).toInstant(), true);
        }

        @Override // org.schabi.newpipe.extractor.InfoItemExtractor
        public String getName() {
            return this.videoObject.getString("title");
        }

        @Override // org.schabi.newpipe.extractor.InfoItemExtractor
        public String getUrl() throws ParsingException {
            return YoutubeStreamLinkHandlerFactory.getInstance().getUrl(this.videoObject.getString("id"));
        }

        @Override // org.schabi.newpipe.extractor.InfoItemExtractor
        @Nonnull
        public List<Image> getThumbnails() throws ParsingException {
            return YoutubeParsingHelper.getThumbnailsFromInfoItem(this.videoObject);
        }
    }
}
